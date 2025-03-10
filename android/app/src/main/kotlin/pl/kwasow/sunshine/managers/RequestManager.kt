package pl.kwasow.sunshine.managers

import android.location.Location
import pl.kwasow.sunshine.data.Album
import pl.kwasow.sunshine.data.AuthenticationResult
import pl.kwasow.sunshine.data.Memory
import pl.kwasow.sunshine.data.UserLocation
import pl.kwasow.sunshine.data.Wish

interface RequestManager {
    // ====== Methods
    suspend fun ping(): Boolean

    suspend fun getAuthenticatedUser(): AuthenticationResult

    suspend fun sendMissingYouMessage(): Boolean

    suspend fun getMemories(): Map<Int, List<Memory>>?

    suspend fun getWishlist(): List<Wish>?

    suspend fun addWish(
        author: String,
        content: String,
        timestamp: Long,
    ): Boolean

    suspend fun updateWish(wish: Wish): Boolean

    suspend fun removeWish(wish: Wish): Boolean

    suspend fun getAlbums(): List<Album>?

    suspend fun getPartnerLocation(cached: Boolean = true): UserLocation?

    suspend fun updateLocation(location: Location): Boolean
}
