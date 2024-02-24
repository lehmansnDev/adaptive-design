package de.lehmansn.adaptivedesign.ui.home.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.ThreePaneScaffoldValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.lehmansn.adaptivedesign.ui.home.HomeActions
import de.lehmansn.adaptivedesign.ui.home.HomeState
import de.lehmansn.adaptivedesign.ui.home.component.ProductListItem
import de.lehmansn.adaptivedesign.ui.home.topbar.SalesTopBar

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SalesContent(
    state: HomeState.Content,
    scaffoldValue: ThreePaneScaffoldValue,
    actions: HomeActions
) {
    Scaffold(
        topBar = {
            SalesTopBar(
                scaffoldValue = scaffoldValue,
                actions = actions,
                state = state
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(state.products) { product ->
                ProductListItem(
                    product,
                    state.shoppingList,
                    actions.productListItemActions
                )
            }
        }
    }
}
