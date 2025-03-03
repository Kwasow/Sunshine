package pl.kwasow.sunshine.ui

import kotlinx.serialization.Serializable

// ====== Main navigation
@Serializable
object HomeScreen

@Serializable
object LoginScreen

@Serializable
object SettingsScreen

// ====== Modules
@Serializable
object MemoriesScreen

@Serializable
object MusicScreen

@Serializable
data class AlbumScreen(val albumUuid: String)

@Serializable
object WishlistScreen

@Serializable
object MissingYouScreen

@Serializable
object LocationScreen
