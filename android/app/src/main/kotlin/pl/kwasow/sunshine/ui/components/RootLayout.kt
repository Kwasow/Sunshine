package pl.kwasow.sunshine.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// ====== Public composables
@Composable
fun RootLayout(
    modifier: Modifier = Modifier.fillMaxSize(),
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {

}
