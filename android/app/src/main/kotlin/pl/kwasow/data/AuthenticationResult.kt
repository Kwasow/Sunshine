package pl.kwasow.data

data class AuthenticationResult(
    val authorization: Authorization,
    val authenticatedUser: User?,
) {
    enum class Authorization {
        AUTHORIZED,
        UNAUTHORIZED,
        UNKNOWN,
    }
}
