package pl.kwasow.managers

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState

interface PermissionManager {
    // ====== Public methods
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @ExperimentalPermissionsApi
    @Composable
    fun rememberNotificationPermissionState(): PermissionState

    @ExperimentalPermissionsApi
    @Composable
    fun rememberLocationPermissionState(): MultiplePermissionsState

    fun launchPermissionSettings(activity: Activity)

    fun checkBackgroundLocationPermission(): Boolean
}
