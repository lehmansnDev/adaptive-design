package de.lehmansn.adaptivedesign.ui.home

import de.lehmansn.adaptivedesign.model.Product
import de.lehmansn.adaptivedesign.model.ShoppingList

sealed interface HomeState {

    data object Loading : HomeState

    data class Content(
        val products: List<Product>,
        val shoppingList: ShoppingList = ShoppingList()
    ) : HomeState

    data class Error(val message: String) : HomeState
}