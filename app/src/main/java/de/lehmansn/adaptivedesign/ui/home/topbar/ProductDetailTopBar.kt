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

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
fun ProductDetailTopBar(
    scaffoldValue: ThreePaneScaffoldValue,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Product") },
        navigationIcon = {
            if (scaffoldValue.tertiary != PaneAdaptedValue.Expanded || scaffoldValue.secondary != PaneAdaptedValue.Expanded) {
                IconButton(
                    onClick = onBackClick
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