package pl.kwasow.managers

import pl.kwasow.data.Memory

interface MemoriesManager {
    // ====== Methods
    suspend fun getMemories(forceRefresh: Boolean = false): Map<Int, List<Memory>>

    suspend fun getTodayMemories(): List<Memory>
}
