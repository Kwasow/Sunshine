package pl.kwasow.sunshine.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.androidx.compose.koinViewModel
import pl.kwasow.sunshine.extensions.slideComposable
import pl.kwasow.sunshine.ui.components.RootLayout
import pl.kwasow.sunshine.ui.composition.LocalSunshineNavigation
import pl.kwasow.sunshine.ui.composition.SunshineNavigation
import pl.kwasow.sunshine.ui.screens.home.HomeScreen
import pl.kwasow.sunshine.ui.screens.login.LoginScreen
import pl.kwasow.sunshine.ui.screens.modules.location.LocationModuleScreen
import pl.kwasow.sunshine.ui.screens.modules.memories.MemoriesModuleScreen
import pl.kwasow.sunshine.ui.screens.modules.missingyou.MissingYouModuleScreen
import pl.kwasow.sunshine.ui.screens.modules.music.AlbumDetailsView
import pl.kwasow.sunshine.ui.screens.modules.music.MusicModuleScreen
import pl.kwasow.sunshine.ui.screens.modules.whishlist.WishlistModuleScreen
import pl.kwasow.sunshine.ui.screens.photo.PhotoPreviewScreen
import pl.kwasow.sunshine.ui.screens.settings.SettingsScreen
import pl.kwasow.sunshine.ui.widgets.music.PlaybackControls

// ====== Public composables
@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        RootLayout(
            bottomBar = { BottomActions() },
            content = { NavContainer(modifier = Modifier.fillMaxSize()) },
        )
    }
}

// ====== Private composables
@Composable
private fun NavContainer(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    SunshineNavHost(
        navController = navController,
        modifier = modifier,
    ) {
        // ====== Main navigation
        slideComposable<HomeScreen> {
            HomeScreen()
        }

        slideComposable<LoginScreen> {
            LoginScreen()
        }

        composable<PhotoScreen> { backStackEntry ->
            val photo: PhotoScreen = backStackEntry.toRoute()

            PhotoPreviewScreen(uri = photo.uri)
        }

        slideComposable<SettingsScreen> {
            SettingsScreen()
        }

        // ====== Modules
        slideComposable<MemoriesScreen> {
            MemoriesModuleScreen()
        }

        slideComposable<MusicScreen> {
            MusicModuleScreen()
        }

        slideComposable<AlbumScreen> { backStackEntry ->
            val album: AlbumScreen = backStackEntry.toRoute()

            AlbumDetailsView(albumUuid = album.albumUuid)
        }

        slideComposable<WishlistScreen> {
            WishlistModuleScreen()
        }

        slideComposable<MissingYouScreen> {
            MissingYouModuleScreen()
        }

        slideComposable<LocationScreen> {
            LocationModuleScreen()
        }
    }
}

@Composable
private fun SunshineNavHost(
    navController: NavHostController,
    modifier: Modifier,
    builder: NavGraphBuilder.() -> Unit,
) {
    val viewModel = koinViewModel<AppViewModel>()
    val sunshineNavigation =
        SunshineNavigation(
            navigateToHome = { navController.navigate(HomeScreen) { popUpTo(0) } },
            navigateToLogin = { navController.navigate(LoginScreen) { popUpTo(0) } },
            navigateToPhoto = { navController.navigate(PhotoScreen(it)) },
            navigateToSettings = { navController.navigate(SettingsScreen) },
            navigateToMemories = { navController.navigate(MemoriesScreen) },
            navigateToMusic = { navController.navigate(MusicScreen) },
            navigateToMusicAlbum = { navController.navigate(AlbumScreen(it)) },
            navigateToWishlist = { navController.navigate(WishlistScreen) },
            navigateToMissingYou = { navController.navigate(MissingYouScreen) },
            navigateToLocation = { navController.navigate(LocationScreen) },
            navigateBack = { navController.popBackStack() },
        )

    CompositionLocalProvider(LocalSunshineNavigation provides sunshineNavigation) {
        NavHost(
            navController = navController,
            startDestination = viewModel.getInitialRoute(),
            modifier = modifier,
            builder = builder,
        )
    }
}

@Composable
private fun BottomActions(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        PlaybackControls()
    }
}
