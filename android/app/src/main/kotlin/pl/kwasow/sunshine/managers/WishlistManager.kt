package pl.kwasow.sunshine.managers

import pl.kwasow.sunshine.data.Wish

interface WishlistManager {
    // ====== Methods
    suspend fun getWishlist(): Map<String, List<Wish>>?

    suspend fun addWish(
        author: String,
        content: String,
    ): Boolean

    suspend fun removeWish(wish: Wish): Boolean

    suspend fun updateWish(wish: Wish): Boolean
}
