package de.lehmansn.adaptivedesign.model

data class Product(
    val id: Int,
    val title: String,
    val price: Float,
    val description: String,
    val category: Category,
    val image: String,
    val rating: Rating,
)