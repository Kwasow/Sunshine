package pl.kwasow.sunshine.managers

import pl.kwasow.sunshine.data.Memory
import pl.kwasow.sunshine.data.User

interface SystemManager {
    // ====== Methods
    fun isInternetAvailable(): Boolean

    fun cacheMemories(memories: Map<Int, List<Memory>>?)

    fun getCachedMemories(): Map<Int, List<Memory>>?

    fun cacheUser(user: User?)

    fun getCachedUser(): User?

    fun clearCache()

    fun clearCoilCache()

    fun launchStore()
}
