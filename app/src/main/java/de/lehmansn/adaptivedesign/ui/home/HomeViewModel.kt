package de.lehmansn.adaptivedesign.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.lehmansn.adaptivedesign.domain.GetProductsUseCase
import de.lehmansn.adaptivedesign.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val getProducts: GetProductsUseCase
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext = viewModelScope.coroutineContext

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun initialize() {
        _state.value = HomeState.Loading
        launch {
            val result = getProducts()
            result.getOrNull()?.let { products ->
                _state.value = HomeState.Content(products)
            } ?: run {
                val exception = result.exceptionOrNull()
                _state.value = HomeState.Error(exception?.message ?: "")
            }
        }
    }

    fun addProductToShoppingList(product: Product) {
        getContentState()?.let { contentState ->
            _state.value = contentState.copy(
                shoppingList = contentState.shoppingList.copy(
                    open = contentState.shoppingList.open + product
                )
            )
        }
    }

    private fun getContentState(): HomeState.Content? {
        return state.value as? HomeState.Content
    }
}