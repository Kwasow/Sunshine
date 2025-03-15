package pl.kwasow.managers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

class PermissionManagerImpl(
    private val context: Context,
) : PermissionManager {
    // ====== Public methods
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @ExperimentalPermissionsApi
    @Composable
    override fun rememberNotificationPermissionState(): PermissionState =
        rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)

    @ExperimentalPermissionsApi
    @Composable
    override fun rememberLocationPermissionState(): MultiplePermissionsState =
        rememberMultiplePermissionsState(
            listOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
            ),
        )

    override fun launchPermissionSettings(activity: Activity) {
        val intent =
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + context.packageName),
            )

        activity.startActivity(intent)
    }

    override fun checkBackgroundLocationPermission(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return true
        }

        val res =
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            )

        return res == android.content.pm.PackageManager.PERMISSION_GRANTED
    }
}
