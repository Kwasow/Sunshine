package pl.kwasow.sunshine.ui.screens.modules.location

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import kotlinx.coroutines.launch
import pl.kwasow.sunshine.data.UserLocation
import pl.kwasow.sunshine.managers.LocationManager
import pl.kwasow.sunshine.managers.PermissionManager
import pl.kwasow.sunshine.managers.UserManager

class LocationModuleViewModel(
    private val locationManager: LocationManager,
    private val permissionManager: PermissionManager,
    private val userManager: UserManager,
) : ViewModel() {
    // ====== Fields
    var isLoading by mutableStateOf(false)
    val userLocation = MutableLiveData<UserLocation?>(null)
    val partnerLocation = MutableLiveData<UserLocation?>(null)

    // ====== Public methods
    fun refreshUserLocation() {
        viewModelScope.launch {
            isLoading = true

            var location = getUserLocation(false)
            if (location != null) {
                userLocation.postValue(location)
            }

            location = getPartnerLocation()
            if (location != null) {
                partnerLocation.postValue(location)
            }

            isLoading = false
        }
    }

    @ExperimentalPermissionsApi
    @Composable
    fun rememberLocationPermissionState(): MultiplePermissionsState =
        permissionManager.rememberLocationPermissionState()

    fun launchPermissionSettings(activity: Activity?) {
        if (activity != null) {
            permissionManager.launchPermissionSettings(activity)
        } else {
            // TODO: Show error
        }
    }

    // ====== Private methods
    private suspend fun getUserLocation(cached: Boolean): UserLocation? {
        val user = userManager.getCachedUser() ?: return null
        val location =
            if (cached) {
                locationManager.getCachedLocation()
            } else {
                locationManager.getCurrentLocation()
            } ?: return null

        return UserLocation(user, location, cached)
    }

    private suspend fun getPartnerLocation(): UserLocation? = locationManager.getPartnerLocation()
}
