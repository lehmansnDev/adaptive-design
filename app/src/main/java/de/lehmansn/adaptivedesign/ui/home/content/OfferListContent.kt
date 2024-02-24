package de.lehmansn.adaptivedesign.ui.home.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.PaneAdaptedValue
import androidx.compose.material3.adaptive.ThreePaneScaffoldValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.lehmansn.adaptivedesign.ui.home.HomeActions
import de.lehmansn.adaptivedesign.ui.home.HomeState
import de.lehmansn.adaptivedesign.ui.home.component.ProductListItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun OfferListContent(
    state: HomeState.Content,
    scaffoldValue: ThreePaneScaffoldValue,
    actions: HomeActions
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Sales") },
                actions = {
                    if (scaffoldValue.primary == PaneAdaptedValue.Hidden) {
                        Button(
                            onClick = actions.topBarActions.onShoppingListClick,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                contentColor = MaterialTheme.colorScheme.onBackground
                            ),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            BadgedBox(
                                badge = {
                                    val number = state.shoppingList.open.size
                                    if (number > 0) {
                                        Badge {
                                            Text(text = "${state.shoppingList.open.size}")
                                        }
                                    }
                                }
                            ) {
                                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                            }
                        }
                    }
                }
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