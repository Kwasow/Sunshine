package pl.kwasow.sunshine.ui.screens.modules.location

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import kotlinx.coroutines.launch
import pl.kwasow.sunshine.managers.LocationManager
import pl.kwasow.sunshine.managers.PermissionManager

class LocationModuleViewModel(
    private val locationManager: LocationManager,
    private val permissionManager: PermissionManager,
) : ViewModel() {
    // ====== Fields
    var isLoading by mutableStateOf(false)
    val userLocation = locationManager.userLocation
    val partnerLocation = locationManager.partnerLocation

    // ====== Public methods
    fun refreshUserLocation() {
        viewModelScope.launch {
            isLoading = true

            requestUserLocation()
            requestPartnerLocation()

            isLoading = false
        }
    }

    @ExperimentalPermissionsApi
    @Composable
    fun rememberLocationPermissionState(): MultiplePermissionsState =
        permissionManager.rememberLocationPermissionState()

    fun launchPermissionSettings(activity: Activity) =
        permissionManager.launchPermissionSettings(activity)

    // ====== Private methods
    private suspend fun requestUserLocation() =
        locationManager.requestLocation()

    private suspend fun requestPartnerLocation() =
        locationManager.requestPartnerLocation(false)
}
