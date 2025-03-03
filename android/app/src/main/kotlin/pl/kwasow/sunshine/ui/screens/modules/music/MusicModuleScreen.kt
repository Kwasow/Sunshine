package pl.kwasow.sunshine.ui.screens.modules.music

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import pl.kwasow.sunshine.ui.components.FlamingoBackgroundLight
import pl.kwasow.sunshine.ui.components.SunshineTopAppBar

// ====== Public composables
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicModuleScreen(
    onBackPressed: () -> Unit,
    navigateToAlbum: (String) -> Unit,
) {
    val viewModel = koinViewModel<MusicModuleViewModel>()

    LaunchedEffect(true) {
        viewModel.refreshAlbumList(true)
    }

    Scaffold(
        topBar = {
            SunshineTopAppBar(
                title = stringResource(MusicModuleInfo.nameId),
                onBackPressed = onBackPressed,
            )
        },
    ) { paddingValues ->
        FlamingoBackgroundLight(modifier = Modifier.padding(paddingValues))

        MainView(
            paddingValues = paddingValues,
            navigateToAlbum = navigateToAlbum,
        )
    }
}

// ====== Private composables
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainView(
    paddingValues: PaddingValues,
    navigateToAlbum: (String) -> Unit,
) {
    val viewModel = koinViewModel<MusicModuleViewModel>()

    fun refresh(force: Boolean = false) {
        viewModel.refreshAlbumList(force)
    }

    PullToRefreshBox(
        isRefreshing = viewModel.isAlbumListRefreshing,
        onRefresh = { refresh(true) },
        modifier =
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
    ) {
        if (viewModel.albumListLoaded) {
            AlbumListView(
                navigateToAlbum = navigateToAlbum,
                albums = viewModel.albumList,
            )
        }
    }
}

// ====== Previews
// TODO: Broken preview
@Preview
@Composable
private fun MusicModuleScreenPreview() {
    MusicModuleScreen(
        onBackPressed = {},
        navigateToAlbum = {},
    )
}
