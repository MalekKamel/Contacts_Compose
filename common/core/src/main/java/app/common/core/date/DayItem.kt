package app.common.core.date

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import java.util.*

data class DayItem(
    var day: Int = 0,
    var name: String = "",
    var date: LocalDate = LocalDate.now(),
    var time: LocalTime? = null
) {
    fun with(localTime: LocalTime): LocalDateTime {
        return LocalDateTime.of(date, localTime)
    }

    fun subDayName(length: Int = 3): DayItem {
        if (name.isEmpty())
            return this
        name = name.substring(0, length).lowercase(Locale.getDefault())
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        return this
    }
}

fun LocalDateTime.toDayItem(): DayItem {
    return DayItem(
        date = toLocalDate(),
        time = toLocalTime(),
        day = dayOfMonth,
        name = dayOfWeek.name
    )
}