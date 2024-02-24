package de.lehmansn.adaptivedesign.domain

import de.lehmansn.adaptivedesign.data.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        productRepository.getProducts()
    }
}