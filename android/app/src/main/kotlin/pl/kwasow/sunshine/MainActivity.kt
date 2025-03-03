package pl.kwasow.sunshine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import org.koin.compose.KoinContext
import pl.kwasow.sunshine.ui.screens.MainComposeView
import pl.kwasow.sunshine.ui.theme.SunshineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSystemBars()

        setContent {
            SunshineTheme {
                KoinContext {
                    MainComposeView()
                }
            }
        }
    }

    private fun setupSystemBars() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}
