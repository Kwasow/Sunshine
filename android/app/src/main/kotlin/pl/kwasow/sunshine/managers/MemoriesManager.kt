package pl.kwasow.sunshine.managers

import pl.kwasow.sunshine.data.Memory

interface MemoriesManager {
    // ====== Methods
    suspend fun getMemories(forceRefresh: Boolean = false): Map<Int, List<Memory>>

    suspend fun getTodayMemories(): List<Memory>
}
