package pl.kwasow.sunshine.ui.screens.home

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.koin.androidx.compose.koinViewModel
import pl.kwasow.sunshine.R
import pl.kwasow.sunshine.ui.components.FlamingoBackground

// ====== Public composables
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    navigateToSettings: () -> Unit,
    navigateToMemories: () -> Unit,
    navigateToMusic: () -> Unit,
    navigateToWishlist: () -> Unit,
    navigateToMissingYou: () -> Unit,
    navigateToLocation: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    val viewModel = koinViewModel<HomeScreenViewModel>()

    LaunchedEffect(true) {
        viewModel.doLaunchTasks(navigateToLogin)
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val notificationPermission = viewModel.rememberNotificationPermissionState()

        LaunchedEffect(true) {
            notificationPermission.launchPermissionRequest()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        FlamingoBackground()

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
        ) {
            TopBar(
                statusBarPadding = paddingValues.calculateTopPadding(),
                navigateToSettings = navigateToSettings,
            )
            WidgetsView()
            ModuleList(
                navigationBarPadding = paddingValues.calculateBottomPadding(),
                navigateToMemories = navigateToMemories,
                navigateToMusic = navigateToMusic,
                navigateToWishlist = navigateToWishlist,
                navigateToMissingYou = navigateToMissingYou,
                navigateToLocation = navigateToLocation,
            )
        }
    }
}

// ====== Private composables
@Composable
private fun TopBar(
    statusBarPadding: Dp,
    navigateToSettings: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = statusBarPadding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.karonia),
            contentDescription = stringResource(id = R.string.contentDescription_karonia_logo),
            modifier =
                Modifier
                    .fillMaxWidth(0.45f)
                    .padding(vertical = 12.dp)
                    .padding(start = 24.dp),
        )

        IconButton(onClick = { navigateToSettings() }) {
            Icon(
                Icons.Outlined.Settings,
                contentDescription = stringResource(id = R.string.contentDescription_settings_icon),
                modifier = Modifier.padding(end = 16.dp),
                tint = Color.DarkGray,
            )
        }
    }
}

// ====== Previews
@Composable
@Preview
private fun HomeScreenPreview() {
    HomeScreen(
        navigateToSettings = {},
        navigateToMemories = {},
        navigateToMusic = {},
        navigateToWishlist = {},
        navigateToMissingYou = {},
        navigateToLocation = {},
        navigateToLogin = {},
    )
}
