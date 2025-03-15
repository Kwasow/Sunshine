package pl.kwasow.managers

import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MessagingManagerImpl(
    private val requestManager: RequestManager,
    private val userManager: UserManager,
) : MessagingManager {
    // ====== Fields
    private val firebaseMessaging = Firebase.messaging

    // ====== Interface methods
    override suspend fun subscribeToTopics() {
        subscribeToUserTopic()
        subscribeToAllTopic()
    }

    override suspend fun sendMissingYou(): Boolean = requestManager.sendMissingYouMessage()

    // ====== Private methods
    private fun subscribeToAllTopic() {
        firebaseMessaging.subscribeToTopic("all")
    }

    private suspend fun subscribeToUserTopic() {
        val user = userManager.getUser()
        user?.userTopic?.let { userTopic ->
            firebaseMessaging.subscribeToTopic(userTopic)
        }
    }
}
