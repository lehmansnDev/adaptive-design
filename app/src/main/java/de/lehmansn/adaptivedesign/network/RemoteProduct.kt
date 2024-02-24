package de.lehmansn.adaptivedesign.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteProduct(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("price")
    val price: Float,
    @SerialName("description")
    val description: String,
    @SerialName("category")
    val category: String,
    @SerialName("image")
    val image: String,
    @SerialName("rating")
    val rating: RemoteRating,
)

@Serializable
data class RemoteRating(
    @SerialName("rate")
    val rate: Float,
    @SerialName("count")
    val count: Int,
)