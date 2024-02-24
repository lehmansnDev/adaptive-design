package de.lehmansn.adaptivedesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import de.lehmansn.adaptivedesign.ui.MainScreen
import de.lehmansn.adaptivedesign.ui.theme.AdaptiveDesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdaptiveDesignTheme {
                MainScreen()
            }
        }
    }
}