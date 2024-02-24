package de.lehmansn.adaptivedesign.ui.home

import de.lehmansn.adaptivedesign.model.Product

data class HomeActions(
    val topBarActions: TopBarActions = TopBarActions(),
    val productListItemActions: ProductListItemActions = ProductListItemActions(),
    val shoppingListActions: ShoppingListActions = ShoppingListActions(),
)

data class TopBarActions(
    val onShoppingListClick: () -> Unit = {}
)

data class ProductListItemActions(
    val onAddClick: (Product) -> Unit = {},
    val onClick: (Product) -> Unit = {}
)

data class ShoppingListActions(
    val onBackClick: () -> Unit = {},
    val onOpenProductClick: (Product) -> Unit = {}
)