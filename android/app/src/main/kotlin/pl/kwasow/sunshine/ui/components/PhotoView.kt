package pl.kwasow.sunshine.ui.components

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import pl.kwasow.sunshine.R

@Composable
fun PhotoView(
    uri: String,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Fit,
) {
    var fullscreen by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val imageRequest =
        ImageRequest.Builder(context)
            .data(uri)
            .diskCacheKey(uri)
            .memoryCacheKey(uri)
            .build()

    AnimatedContent(
        targetState = fullscreen,
        label = "fullscreen_photo_transition",
    ) { targetState ->
        if (!targetState) {
            SmallPhoto(
                imageRequest = imageRequest,
                modifier = modifier,
                contentDescription = contentDescription,
                contentScale = contentScale,
                onClick = { fullscreen = true },
            )
        } else {
            FullscreenPhoto(
                imageRequest = imageRequest,
                contentDescription = contentDescription,
                onDismiss = { fullscreen = false },
            )
        }
    }
}

@Composable
fun SimplePhotoView(
    uri: Uri,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Fit,
) {
    SimplePhotoView(
        uri = uri.toString(),
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale,
    )
}

@Composable
fun SimplePhotoView(
    uri: String,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Fit,
) {
    val context = LocalContext.current
    val imageRequest =
        ImageRequest.Builder(context)
            .data(uri)
            .diskCacheKey(uri)
            .memoryCacheKey(uri)
            .build()

    SmallPhoto(
        imageRequest = imageRequest,
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale,
    )
}

@Composable
private fun SmallPhoto(
    imageRequest: ImageRequest,
    modifier: Modifier,
    contentDescription: String,
    contentScale: ContentScale,
    onClick: (() -> Unit)? = null,
) {
    val mod =
        if (onClick == null) {
            modifier
        } else {
            modifier.clickable {
                println("Test")
                onClick()
            }
        }

    SubcomposeAsyncImage(
        model = imageRequest,
        contentDescription = contentDescription,
        modifier = mod,
        loading = {
            LoadingView(modifier = Modifier.fillMaxSize())
        },
        error = { ErrorView() },
        contentScale = contentScale,
        alignment = Alignment.Center,
    )
}

@Composable
private fun FullscreenPhoto(
    imageRequest: ImageRequest,
    contentDescription: String,
    onDismiss: () -> Unit,
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { onDismiss() },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black,
        ) {
            SubcomposeAsyncImage(
                model = imageRequest,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                loading = {
                    LoadingView(modifier = Modifier.fillMaxSize())
                },
                error = { ErrorView() },
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
            )
        }
    }
}

@Composable
private fun ErrorView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(id = R.string.failed_loading_photo),
            textAlign = TextAlign.Center,
        )
    }
}
