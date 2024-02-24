package de.lehmansn.adaptivedesign.data

import de.lehmansn.adaptivedesign.model.Product
import de.lehmansn.adaptivedesign.network.ShopRemoteSource

class ProductRepository(
    private val shopRemoteSource: ShopRemoteSource,
    private val toProduct: ProductMapper
) {
    suspend fun getProducts(): Result<List<Product>> {
        return try {
            val products = shopRemoteSource.getProducts().map(toProduct::invoke)
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}