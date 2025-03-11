package pl.kwasow.sunshine.ui.screens.photo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pl.kwasow.sunshine.ui.components.PhotoView

// ====== Public composables
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhotoPreviewScreen(uri: String) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
    ) { paddingValues ->
        PhotoView(
            modifier = Modifier.fillMaxSize(),
            uri = uri,
            clickable = false,
        )
    }
}
