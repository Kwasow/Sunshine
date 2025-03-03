package pl.kwasow.sunshine.managers

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import pl.kwasow.sunshine.data.AuthenticationResult
import pl.kwasow.sunshine.data.User

interface UserManager {
    // ====== Classes
    data class LoginContext(
        val context: Context,
        val coroutineScope: CoroutineScope,
        val onSuccess: () -> Unit,
        val onError: (String) -> Unit,
    )

    // ====== Methods
    fun isUserLoggedIn(): Boolean

    suspend fun getAuthenticatedUser(): AuthenticationResult

    suspend fun getUser(): User?

    fun getCachedUser(): User?

    fun launchGoogleLogin(loginContext: LoginContext)

    fun signOut()
}
