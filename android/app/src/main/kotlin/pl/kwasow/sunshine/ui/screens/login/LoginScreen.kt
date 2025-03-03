package pl.kwasow.sunshine.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import pl.kwasow.sunshine.R
import pl.kwasow.sunshine.ui.components.ErrorDialog
import pl.kwasow.sunshine.ui.components.FlamingoBackground
import pl.kwasow.sunshine.ui.components.LoadingView

// ====== Public composables
@Composable
fun LoginScreen(navigateToHome: () -> Unit) {
    val viewModel = koinViewModel<LoginScreenViewModel>()

    Box(modifier = Modifier.fillMaxSize()) {
        FlamingoBackground(modifier = Modifier)

        MainView(navigateToHome = navigateToHome)

        if (viewModel.isLoading) {
            // We use the clickable modifier so that the loading indicator intercepts
            // any clicks and prevents the user from interacting with buttons below
            // the loading indicator layer

            LoadingView(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color(0x80000000))
                        .clickable(enabled = false) {},
            )
        }

        ErrorDialog(
            title = stringResource(id = R.string.login_error),
            error = viewModel.error,
            isShowing = viewModel.error.isNotEmpty(),
            clearError = { viewModel.clearError() },
        )
    }
}

// ====== Private composables
@Composable
private fun MainView(navigateToHome: () -> Unit) {
    val viewModel = koinViewModel<LoginScreenViewModel>()

    Column(
        modifier = Modifier.systemBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            modifier =
                Modifier
                    .weight(0.5f)
                    .padding(horizontal = 32.dp),
            painter = painterResource(id = R.drawable.karonia),
            contentDescription = stringResource(id = R.string.contentDescription_karonia_logo),
        )
        GoogleSignInButton(onClick = { viewModel.launchLogin(navigateToHome) })
    }
}

// ====== Previews
// TODO: Broken preview
@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(navigateToHome = {})
}
