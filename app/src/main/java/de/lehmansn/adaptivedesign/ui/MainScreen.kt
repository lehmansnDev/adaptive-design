package de.lehmansn.adaptivedesign.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import de.lehmansn.adaptivedesign.ui.home.HomeScreen

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
@Composable
fun MainScreen() {
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            home(selected = true, onClick = {})
            schedule(selected = false, onClick = {})
            email(selected = false, onClick = {})
        }
    ) {
        Navigator(HomeScreen)
    }
}

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
private fun NavigationSuiteScope.home(
    selected: Boolean,
    onClick: () -> Unit
) {
    item(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(imageVector = Icons.Rounded.Home, contentDescription = "Home")
        },
        label = {
            Text(text = "Home")
        }
    )
}

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
private fun NavigationSuiteScope.schedule(
    selected: Boolean,
    onClick: () -> Unit
) {
    item(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(imageVector = Icons.Rounded.DateRange, contentDescription = "Schedule")
        },
        label = {
            Text(text = "Schedule")
        }
    )
}

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
private fun NavigationSuiteScope.email(
    selected: Boolean,
    onClick: () -> Unit
) {
    item(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(imageVector = Icons.Rounded.Email, contentDescription = "Email")
        },
        label = {
            Text(text = "Email")
        }
    )
}
