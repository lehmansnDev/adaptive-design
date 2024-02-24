package de.lehmansn.adaptivedesign.ui.home.content

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.lehmansn.adaptivedesign.model.Product
import de.lehmansn.adaptivedesign.model.ShoppingList
import de.lehmansn.adaptivedesign.ui.home.ShoppingListActions
import de.lehmansn.adaptivedesign.ui.home.component.OpenProductShoppingListItem
import de.lehmansn.adaptivedesign.ui.home.topbar.ShoppingListTopBar

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ShoppingListContent(
    shoppingList: ShoppingList,
    navigator: ThreePaneScaffoldNavigator<Product>,
    actions: ShoppingListActions
) {
    if (navigator.canNavigateBack()) {
        BackHandler {
            navigator.navigateBack()
        }
    }

    Scaffold(
        topBar = {
            ShoppingListTopBar(
                scaffoldValue = navigator.scaffoldState.scaffoldValue,
                actions = actions
            )
        }
    ) { paddingValues ->
        BoxWithConstraints(modifier = Modifier.padding(paddingValues)) {
            val fixedColumns = maxWidth / 120.dp

            LazyVerticalGrid(
                columns = GridCells.Fixed(fixedColumns.toInt()),
            ) {
                items(shoppingList.open) { product ->
                    OpenProductShoppingListItem(product)
                }
            }
        }
    }
}
