package pl.kwasow.sunshine.ui.screens.settings

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.kwasow.sunshine.R
import pl.kwasow.sunshine.managers.PermissionManager
import pl.kwasow.sunshine.managers.SettingsManager
import pl.kwasow.sunshine.managers.SystemManager
import pl.kwasow.sunshine.managers.UserManager

class SettingsScreenViewModel(
    private val applicationContext: Context,
    private val permissionManager: PermissionManager,
    private val settingsManager: SettingsManager,
    private val systemManager: SystemManager,
    private val userManager: UserManager,
) : ViewModel() {
    // ====== Fields
    var allowLocationRequests =
        MutableLiveData(
            settingsManager.allowLocationRequests &&
                permissionManager.checkBackgroundLocationPermission(),
        )
        private set

    val partnerName =
        userManager.getCachedUser()?.missingYouRecipient?.firstName
            ?: applicationContext.getString(R.string.partner)

    // ====== Methods
    fun freeUpMemory() {
        systemManager.clearCoilCache()
        Toast.makeText(
            applicationContext,
            applicationContext.getString(R.string.settings_cache_cleared),
            Toast.LENGTH_SHORT,
        ).show()
    }

    fun signOut(onSuccess: () -> Unit) {
        userManager.signOut()
        onSuccess()
    }

    fun launchStore() = systemManager.launchStore()

    fun launchPermissionSettings(activity: Activity) =
        permissionManager.launchPermissionSettings(activity)

    fun toggleAllowLocationRequests(onPermissionMissing: () -> Unit) {
        if (!permissionManager.checkBackgroundLocationPermission()) {
            onPermissionMissing()
        } else {
            val newValue = allowLocationRequests.value != true
            settingsManager.allowLocationRequests = newValue
            allowLocationRequests.value = newValue
        }
    }
}
