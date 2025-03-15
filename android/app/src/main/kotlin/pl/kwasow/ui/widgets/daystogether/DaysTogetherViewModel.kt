package pl.kwasow.ui.widgets.daystogether

import androidx.lifecycle.ViewModel
import pl.kwasow.BuildConfig
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class DaysTogetherViewModel : ViewModel() {
    // ====== Fields
    val start: LocalDate = LocalDate.parse(BuildConfig.RELATIONSHIP_START)

    // ====== Public methods
    fun getDaysTogether(): Long = ChronoUnit.DAYS.between(start, LocalDate.now())
}
