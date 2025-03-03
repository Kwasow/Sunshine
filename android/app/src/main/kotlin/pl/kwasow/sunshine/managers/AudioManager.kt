package pl.kwasow.sunshine.managers

import android.net.Uri
import pl.kwasow.sunshine.data.Album
import pl.kwasow.sunshine.data.AudioTrack

interface AudioManager {
    // ====== Methods
    suspend fun getAlbums(forceRefresh: Boolean = false): List<Album>

    fun getAlbum(uuid: String): Album?

    fun getAlbumCoverUri(album: Album): Uri

    fun getTrackUri(track: AudioTrack): Uri

    fun getTrackId(track: AudioTrack): String

    fun isAlbumDownloaded(album: Album): Boolean

    fun downloadAlbum(album: Album)

    fun removeAlbum(album: Album)

    fun removeAllDownloads()
}
