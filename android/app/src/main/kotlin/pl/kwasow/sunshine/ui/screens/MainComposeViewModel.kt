package pl.kwasow.sunshine.ui.screens

import androidx.lifecycle.ViewModel
import pl.kwasow.sunshine.managers.UserManager
import pl.kwasow.sunshine.ui.HomeScreen
import pl.kwasow.sunshine.ui.LoginScreen

// [IMPORTANT]
//
// This is not a global view model! Don't use it as such! It is a view model for the
// MainComposeView, so a more suitable name would be MainComposeViewViewModel, but I just
// think that is stupid and too long.
class MainComposeViewModel(
    private val userManager: UserManager,
) : ViewModel() {
    // ====== Public methods
    fun getInitialRoute(): Any {
        return if (userManager.isUserLoggedIn()) {
            HomeScreen
        } else {
            LoginScreen
        }
    }
}
