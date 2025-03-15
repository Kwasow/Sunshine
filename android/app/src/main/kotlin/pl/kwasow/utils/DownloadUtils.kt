package pl.kwasow.utils

import android.content.Context
import androidx.media3.database.DatabaseProvider
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.NoOpCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import androidx.media3.exoplayer.offline.DownloadManager
import androidx.media3.exoplayer.offline.DownloadNotificationHelper
import java.io.File
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.Executors

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
object DownloadUtils {
    // ====== Fields
    private const val DOWNLOADS_DIR = "audio_downloads"

    private var downloadNotificationHelper: DownloadNotificationHelper? = null
    private var downloadManager: DownloadManager? = null
    private var databaseProvider: DatabaseProvider? = null
    private var downloadCache: Cache? = null
    private var sourceFactory: DataSource.Factory? = null

    // ====== Public methods
    fun getDownloadManager(context: Context): DownloadManager {
        synchronized(this) {
            val manager =
                downloadManager ?: DownloadManager(
                    context,
                    getDatabaseProvider(context),
                    getDownloadCache(context),
                    getHttpSourceFactory(),
                    Executors.newFixedThreadPool(4),
                )
            downloadManager = manager

            return manager
        }
    }

    fun getDownloadNotificationHelper(
        context: Context,
        channelId: String,
    ): DownloadNotificationHelper {
        val helper = downloadNotificationHelper ?: DownloadNotificationHelper(context, channelId)
        downloadNotificationHelper = helper

        return helper
    }

    fun getDownloadCache(context: Context): Cache {
        synchronized(this) {
            var cache = downloadCache
            if (cache == null) {
                val downloadDirectory = File(context.filesDir, DOWNLOADS_DIR)
                cache =
                    SimpleCache(downloadDirectory, NoOpCacheEvictor(), getDatabaseProvider(context))
            }
            downloadCache = cache

            return cache
        }
    }

    fun getHttpSourceFactory(): DataSource.Factory {
        synchronized(this) {
            var factory = sourceFactory
            if (factory == null) {
                val cookieManager = CookieManager()
                cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER)
                CookieHandler.setDefault(cookieManager)
                factory = DefaultHttpDataSource.Factory()
            }
            sourceFactory = factory

            return factory
        }
    }

    // ====== Private methods
    private fun getDatabaseProvider(context: Context): DatabaseProvider {
        val provider = databaseProvider ?: StandaloneDatabaseProvider(context)
        databaseProvider = provider

        return provider
    }
}
