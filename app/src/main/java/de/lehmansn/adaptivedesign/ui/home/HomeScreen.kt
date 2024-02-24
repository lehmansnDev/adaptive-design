package de.lehmansn.adaptivedesign.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.adaptive.AnimatedPane
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.PaneScaffoldDirective
import androidx.compose.material3.adaptive.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.calculateListDetailPaneScaffoldState
import androidx.compose.material3.adaptive.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.lehmansn.adaptivedesign.model.Product
import de.lehmansn.adaptivedesign.ui.home.content.ErrorContent
import de.lehmansn.adaptivedesign.ui.home.content.LoadingContent
import de.lehmansn.adaptivedesign.ui.home.content.OfferListContent
import de.lehmansn.adaptivedesign.ui.home.content.ProductDetailContent
import de.lehmansn.adaptivedesign.ui.home.content.ShoppingListContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

    val scaffoldState = calculateListDetailPaneScaffoldState()
    val navigator = rememberListDetailPaneScaffoldNavigator<Product>(
        scaffoldDirective = PaneScaffoldDirective(
            contentPadding = PaddingValues(0.dp),
            maxHorizontalPartitions = when (state) {
                is HomeState.Content -> scaffoldState.scaffoldDirective.maxHorizontalPartitions
                is HomeState.Error,
                HomeState.Loading -> 1
            },
            horizontalPartitionSpacerSize = 0.dp,
            maxVerticalPartitions = 1,
            verticalPartitionSpacerSize = 0.dp,
            excludedBounds = emptyList()
        ),
    )

    HomeScaffold(
        state = state,
        navigator = navigator,
        actions = HomeActions(
            topBarActions = TopBarActions(
                onShoppingListClick = {
                    if (navigator.currentDestination?.pane == ListDetailPaneScaffoldRole.Extra) {
                        navigator.navigateBack()
                    } else {
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                    }
                }
            ),
            productListItemActions = ProductListItemActions(
                onAddClick = { viewModel.addProductToShoppingList(it) },
                onClick = {
                    navigator.navigateTo(ListDetailPaneScaffoldRole.Extra, it)
                }
            ),
            shoppingListActions = ShoppingListActions(
                onBackClick = { navigator.navigateBack() }
            )
        )
    )
}

@Composable
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun HomeScaffold(
    state: HomeState,
    navigator: ThreePaneScaffoldNavigator<Product>,
    actions: HomeActions
) {
    ListDetailPaneScaffold(
        listPane = {
            AnimatedPane(modifier = Modifier) {
                when (state) {
                    is HomeState.Content -> OfferListContent(
                        state = state,
                        scaffoldValue = navigator.scaffoldState.scaffoldValue,
                        actions = actions
                    )

                    is HomeState.Error -> ErrorContent(state.message)
                    HomeState.Loading -> LoadingContent()
                }
            }
        },
        detailPane = {
            AnimatedPane(modifier = Modifier) {
                when (state) {
                    is HomeState.Content -> ShoppingListContent(
                        shoppingList = state.shoppingList,
                        scaffoldValue = navigator.scaffoldState.scaffoldValue,
                        actions = actions.shoppingListActions
                    )

                    is HomeState.Error, HomeState.Loading -> Unit
                }
            }
        },
        extraPane = {
            AnimatedPane(modifier = Modifier) {
                navigator.currentDestination?.content?.let { product ->
                    ProductDetailContent(
                        product = product,
                        scaffoldValue = navigator.scaffoldState.scaffoldValue,
                        onBackClick = actions.shoppingListActions.onBackClick
                    )
                }
            }
        },
        scaffoldState = navigator.scaffoldState,
    )
}