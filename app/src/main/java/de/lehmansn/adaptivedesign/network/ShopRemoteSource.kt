package de.lehmansn.adaptivedesign.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ShopRemoteSource(private val client: HttpClient) {

    suspend fun getProducts(): List<RemoteProduct> =
        client.get("https://fakestoreapi.com/products")
}