package pl.kwasow.sunshine.data

import android.location.Location

data class UserLocation(
    val userId: Int,
    val userName: String,
    val latitude: Double,
    val longitude: Double,
    val accuracy: Float,
    val timestamp: Long,
    val isCached: Boolean,
) {
    constructor(user: MinimalUser, location: Location, isCached: Boolean) : this(
        user.id,
        user.firstName,
        location.latitude,
        location.longitude,
        location.accuracy,
        location.time,
        isCached,
    )
}
