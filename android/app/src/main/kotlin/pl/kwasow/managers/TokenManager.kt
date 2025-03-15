package pl.kwasow.managers

interface TokenManager {
    // ====== Methods
    suspend fun getToken(): String?
}
