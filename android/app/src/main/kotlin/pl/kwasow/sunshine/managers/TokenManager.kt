package pl.kwasow.sunshine.managers

interface TokenManager {
    // ====== Methods
    suspend fun getToken(): String?
}
