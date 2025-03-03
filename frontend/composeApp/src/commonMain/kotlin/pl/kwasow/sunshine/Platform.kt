package pl.kwasow.sunshine

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform