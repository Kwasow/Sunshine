import {onSchedule} from "firebase-functions/v2/scheduler"
import * as logger from "firebase-functions/logger"
import {getMessaging, TopicMessage} from "firebase-admin/messaging"
import {initializeApp} from "firebase-admin/app"

initializeApp()

exports.dailymemory = onSchedule(
  {
    schedule: "every day 7:30",
    timeZone: "Europe/Warsaw",
  },
  async () => {
    logger.log("[Daily memory] Starting...")

    const message: TopicMessage = {
      data: {
        type: "daily_memory",
      },
      topic: "all",
    }

    getMessaging().send(message)
      .then((response) => {
        logger.log("[Daily memory] Successfully sent message:", response)
      })
      .catch((error) => {
        logger.log("[Daily memory] Error sending message:", error)
      })
  }
)
