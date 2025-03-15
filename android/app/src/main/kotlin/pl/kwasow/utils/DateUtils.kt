package pl.kwasow.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId

object DateUtils {
    // ====== Public methods
    fun timestampToString(timestamp: Long): String {
        val instant = Instant.ofEpochMilli(timestamp)
        val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

        val month =
            when (date.month) {
                Month.JANUARY -> "stycznia"
                Month.FEBRUARY -> "lutego"
                Month.MARCH -> "marca"
                Month.APRIL -> "kwietnia"
                Month.MAY -> "maja"
                Month.JUNE -> "czerwca"
                Month.JULY -> "lipca"
                Month.AUGUST -> "sierpnia"
                Month.SEPTEMBER -> "września"
                Month.OCTOBER -> "października"
                Month.NOVEMBER -> "listopada"
                Month.DECEMBER -> "grudnia"
                else -> NullPointerException("date.month cannot be null")
            }

        return "${date.dayOfMonth} $month ${date.year}"
    }
}
