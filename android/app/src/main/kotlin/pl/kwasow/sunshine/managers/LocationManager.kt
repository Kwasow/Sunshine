package pl.kwasow.sunshine.managers

import android.location.Location
import pl.kwasow.sunshine.data.UserLocation

interface LocationManager {
    // ====== Public methods
    suspend fun getCachedLocation(): Location?

    suspend fun getCurrentLocation(): Location?

    suspend fun getPartnerLocation(): UserLocation?

    suspend fun sendLocationToPartner()
}
