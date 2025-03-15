package pl.kwasow.managers

import pl.kwasow.data.NotificationChannelInfo

interface NotificationManager {
    // ====== Fields
    val memoriesChannelInfo: NotificationChannelInfo

    val downloadChannelInfo: NotificationChannelInfo

    val missingYouChannelInfo: NotificationChannelInfo

    // ====== Methods
    fun postMemoryNotification()

    fun postMissingYouNotification(senderName: String)
}
