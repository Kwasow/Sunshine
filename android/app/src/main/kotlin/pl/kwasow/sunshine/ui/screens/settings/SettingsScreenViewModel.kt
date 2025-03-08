package pl.kwasow.sunshine.ui.screens.settings

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import pl.kwasow.sunshine.R
import pl.kwasow.sunshine.managers.SystemManager
import pl.kwasow.sunshine.managers.UserManager

class SettingsScreenViewModel(
    private val applicationContext: Context,
    private val systemManager: SystemManager,
    private val userManager: UserManager,
) : ViewModel() {
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
}
