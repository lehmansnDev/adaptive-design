package de.lehmansn.adaptivedesign.ui.home.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.PaneAdaptedValue
import androidx.compose.material3.adaptive.ThreePaneScaffoldValue
import androidx.compose.runtime.Composable
import de.lehmansn.adaptivedesign.ui.home.ShoppingListActions

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class) 
fun ShoppingListTopBar(
    scaffoldValue: ThreePaneScaffoldValue,
    actions: ShoppingListActions
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Shoppinglist") },
        navigationIcon = {
            if (scaffoldValue.primary != PaneAdaptedValue.Expanded || scaffoldValue.secondary != PaneAdaptedValue.Expanded) {
                IconButton(
                    onClick = actions.onBackClick
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
            }
        }
    )
}