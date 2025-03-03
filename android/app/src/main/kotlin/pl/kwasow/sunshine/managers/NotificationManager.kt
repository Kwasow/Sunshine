package pl.kwasow.sunshine.managers

import pl.kwasow.sunshine.data.NotificationChannelInfo

interface NotificationManager {
    // ====== Fields
    val memoriesChannelInfo: NotificationChannelInfo

    val downloadChannelInfo: NotificationChannelInfo

    val missingYouChannelInfo: NotificationChannelInfo

    // ====== Methods
    fun postMemoryNotification()

    fun postMissingYouNotification(senderName: String)
}
