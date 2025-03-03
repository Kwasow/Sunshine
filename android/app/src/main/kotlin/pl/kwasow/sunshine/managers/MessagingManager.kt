package pl.kwasow.sunshine.managers

interface MessagingManager {
    // ====== Classes
    enum class MessageType(val id: String) {
        MISSING_YOU("missing_you"),
        DAILY_MEMORY("daily_memory"),
        REQUEST_LOCATION("request_location"),
        ;

        companion object {
            fun fromString(value: String): MessageType? = entries.find { it.id == value }
        }
    }

    // ====== Methods
    suspend fun subscribeToTopics()

    suspend fun sendMissingYou(): Boolean
}
