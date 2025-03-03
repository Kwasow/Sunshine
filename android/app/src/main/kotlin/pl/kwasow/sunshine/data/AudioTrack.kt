package pl.kwasow.sunshine.data

import kotlinx.serialization.Serializable

@Serializable
class AudioTrack(
    val id: Int,
    val title: String,
    val comment: String? = null,
    val resourceName: String,
    val albumUuid: String,
)
