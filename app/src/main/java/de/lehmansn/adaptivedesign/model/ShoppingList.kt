package de.lehmansn.adaptivedesign.model

data class ShoppingList(
    val open: List<Product> = emptyList(),
    val closed: List<Product> = emptyList()
)
