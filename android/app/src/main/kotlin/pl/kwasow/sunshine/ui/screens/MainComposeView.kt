package pl.kwasow.sunshine.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.androidx.compose.koinViewModel
import pl.kwasow.sunshine.extensions.slideComposable
import pl.kwasow.sunshine.ui.AlbumScreen
import pl.kwasow.sunshine.ui.HomeScreen
import pl.kwasow.sunshine.ui.LocationScreen
import pl.kwasow.sunshine.ui.LoginScreen
import pl.kwasow.sunshine.ui.MemoriesScreen
import pl.kwasow.sunshine.ui.MissingYouScreen
import pl.kwasow.sunshine.ui.MusicScreen
import pl.kwasow.sunshine.ui.SettingsScreen
import pl.kwasow.sunshine.ui.WishlistScreen
import pl.kwasow.sunshine.ui.screens.home.HomeScreen
import pl.kwasow.sunshine.ui.screens.login.LoginScreen
import pl.kwasow.sunshine.ui.screens.modules.location.LocationModuleScreen
import pl.kwasow.sunshine.ui.screens.modules.memories.MemoriesModuleScreen
import pl.kwasow.sunshine.ui.screens.modules.missingyou.MissingYouModuleScreen
import pl.kwasow.sunshine.ui.screens.modules.music.AlbumDetailsView
import pl.kwasow.sunshine.ui.screens.modules.music.MusicModuleScreen
import pl.kwasow.sunshine.ui.screens.modules.whishlist.WishlistModuleScreen
import pl.kwasow.sunshine.ui.screens.settings.SettingsScreen
import pl.kwasow.sunshine.ui.widgets.music.PlaybackControls

// ====== Public composables
@Composable
fun MainComposeView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        ConstraintLayout {
            val playbackControls = createRef()

            NavContainer(modifier = Modifier.fillMaxSize())
            BottomActions(
                modifier =
                    Modifier.constrainAs(playbackControls) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
            )
        }
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
            HomeScreen(
                navigateToSettings = { navController.navigate(SettingsScreen) },
                navigateToMemories = { navController.navigate(MemoriesScreen) },
                navigateToMusic = { navController.navigate(MusicScreen) },
                navigateToWishlist = { navController.navigate(WishlistScreen) },
                navigateToMissingYou = { navController.navigate(MissingYouScreen) },
                navigateToLocation = { navController.navigate(LocationScreen) },
                navigateToLogin = { navController.navigate(LoginScreen) { popUpTo(0) } },
            )
        }

        slideComposable<LoginScreen> {
            LoginScreen(navigateToHome = {
                navController.navigate(HomeScreen) { popUpTo(0) }
            })
        }

        slideComposable<SettingsScreen> {
            SettingsScreen(
                onBackPressed = { navController.popBackStack() },
                onLogout = { navController.navigate(LoginScreen) { popUpTo(0) } },
            )
        }

        // ====== Modules
        slideComposable<MemoriesScreen> {
            MemoriesModuleScreen(onBackPressed = { navController.popBackStack() })
        }

        slideComposable<MusicScreen> {
            MusicModuleScreen(
                onBackPressed = { navController.popBackStack() },
                navigateToAlbum = { albumUuid ->
                    navController.navigate(AlbumScreen(albumUuid = albumUuid))
                },
            )
        }
        slideComposable<AlbumScreen> { backStackEntry ->
            val album: AlbumScreen = backStackEntry.toRoute()

            AlbumDetailsView(
                onBackPressed = { navController.popBackStack() },
                albumUuid = album.albumUuid,
            )
        }

        slideComposable<WishlistScreen> {
            WishlistModuleScreen(onBackPressed = { navController.popBackStack() })
        }

        slideComposable<MissingYouScreen> {
            MissingYouModuleScreen(onBackPressed = { navController.popBackStack() })
        }

        slideComposable<LocationScreen> {
            LocationModuleScreen(onBackPressed = { navController.popBackStack() })
        }
    }
}

@Composable
private fun SunshineNavHost(
    navController: NavHostController,
    modifier: Modifier,
    builder: NavGraphBuilder.() -> Unit,
) {
    val viewModel = koinViewModel<MainComposeViewModel>()

    NavHost(
        navController = navController,
        startDestination = viewModel.getInitialRoute(),
        modifier = modifier,
        builder = builder,
    )
}

@Composable
private fun BottomActions(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        PlaybackControls()
    }
}
