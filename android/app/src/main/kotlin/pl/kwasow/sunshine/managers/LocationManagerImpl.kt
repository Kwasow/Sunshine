package pl.kwasow.sunshine.managers

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import pl.kwasow.sunshine.data.UserLocation
import pl.kwasow.sunshine.utils.SunshineLogger

class LocationManagerImpl(
    context: Context,
    private val requestManager: RequestManager,
    private val settingsManager: SettingsManager,
) : LocationManager {
    // ====== Fields
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override val userLocation: LiveData<Location?> = MutableLiveData(null)
    override val partnerLocation: LiveData<UserLocation?> = MutableLiveData(null)

    // ====== Interface methods
    override suspend fun requestLocation() {
        TODO("Not yet implemented")
    }

    override suspend fun requestPartnerLocation() {
        TODO("Not yet implemented")
    }

//    override suspend fun getCachedLocation(): Location? {
//        try {
//            val location: Location? = fusedLocationClient.lastLocation.await()
//            updateLocationOnServer(location)
//
//            return location
//        } catch (e: SecurityException) {
//            SunshineLogger.e("Location permission not granted", e)
//            return null
//        } catch (e: Exception) {
//            SunshineLogger.e("Error getting location", e)
//            return null
//        }
//    }
//
//    override suspend fun getCurrentLocation(): Location? {
//        val accuracy = Priority.PRIORITY_BALANCED_POWER_ACCURACY
//
//        try {
//            val location: Location? =
//                fusedLocationClient.getCurrentLocation(
//                    accuracy,
//                    CancellationTokenSource().token,
//                ).await()
//            updateLocationOnServer(location)
//
//            return location
//        } catch (e: SecurityException) {
//            SunshineLogger.e("Location permission not granted", e)
//            return null
//        } catch (e: Exception) {
//            SunshineLogger.e("Error getting location", e)
//            return null
//        }
//    }
//
//    override suspend fun getPartnerLocation(): UserLocation? = requestManager.getPartnerLocation()
//
//    override suspend fun sendLocationToPartner() {
//        if (!settingsManager.allowLocationRequests) {
//            return
//        }
//
//        getCurrentLocation()
//    }

    // ====== Private methods
    private suspend fun updateLocationOnServer(location: Location?) {
        if (location == null) {
            return
        }

        if (requestManager.updateLocation(location)) {
            SunshineLogger.i("Location updated on server")
        } else {
            SunshineLogger.e("Location update on server failed")
        }
    }
}
