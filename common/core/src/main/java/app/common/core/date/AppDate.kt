package app.common.core.date

import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

/*
server date example: 08-08-2021 17:05
 */
object AppDate {

    enum class Format(val value: String) {
        SERVER_DATE("dd-MM-yyyy HH:mm"),
        DDDD("EEEE"),
        MM_DD_YY_HH_MM_SS("MM-dd-yy hh:mm"),
        YY_MM_DD_HH_MM_SS("yy-MM-dd hh:mm:ss a"),
        YY_MM_DD_H_MM_SS("yyyy-MM-dd h:m a"),
        YY_MM_DD_HH_MM("yyyy-MM-dd hh:mm"),
        YYYY_MM_DD_HH_MM_A("dd/MM/yyyy, hh:mm a"),
        DD_MM_YY("dd-MM-yyyy"),
        DD_MM_YY_("dd/MM/yyyy"),
        HH_A("hh:mm a"),
        MM_SS("mm:ss"),
        HH_MM("H:mm"),
        H_MM("h:mm a"),
        AM_PM("a"),
        H("h"),
        M("m"),
    }

    fun format(date: LocalDateTime, format: Format, locale: Locale? = null): String {
        val formatter = DateTimeFormatter.ofPattern(format.value)
        if (locale != null)
            formatter.withLocale(locale)
        return date.format(formatter)
    }

    fun format(date: LocalDate, format: Format): String {
        return date.format(DateTimeFormatter.ofPattern(format.value))
    }

    fun format(date: String, format: Format = Format.SERVER_DATE): String {
        return localDatetime(date).format(DateTimeFormatter.ofPattern(format.value))
    }

    private fun localDatetime(date: String, format: Format = Format.SERVER_DATE): LocalDateTime {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format.value))
    }

    private fun localDate(date: String, format: Format = Format.SERVER_DATE): LocalDate {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format.value))
    }

    fun localTime(date: String, format: Format = Format.SERVER_DATE): LocalTime {
        return LocalTime.parse(date, DateTimeFormatter.ofPattern(format.value))
    }

    fun format(milli: Long, format: Format = Format.SERVER_DATE): String {
        val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(milli), ZoneId.systemDefault())
        return date.format(DateTimeFormatter.ofPattern(format.value))
    }

    fun format(dateTime: LocalDateTime, format: Format = Format.SERVER_DATE): String {
        return dateTime.format(DateTimeFormatter.ofPattern(format.value))
    }

    fun durationInDaysOf(lhs: String, rhs: String): Long {
        val localDate1 = localDate(lhs)
        val localDate2 = localDate(rhs)
        return Duration.between(localDate1.atStartOfDay(), localDate2.atStartOfDay()).toDays()
    }

    fun nextDaysOf(dateTime: LocalDateTime, daysSize: Int): List<DayItem> {
        val daysItems = mutableListOf<DayItem>()
        daysItems.add(dateTime.toDayItem().subDayName())

        var date = dateTime
        for (i in 0 until daysSize) {
            date = date.plusDays(1)
            daysItems.add(date.toDayItem().subDayName())
        }
        return daysItems
    }

    fun timeType(dateTime: LocalDateTime): AmPm? {
        val formatter = format(dateTime, Format.AM_PM, Locale.ENGLISH)
        return AmPm.from(formatter)
    }

    fun currentMillis() =    Calendar.getInstance().timeInMillis
}

fun String.formatDate(format: AppDate.Format) = AppDate.format(this, format)

fun Long.formatDate(format: AppDate.Format) = AppDate.format(this, format)

fun LocalDateTime.nextDays(days: Int) = AppDate.nextDaysOf(this, days)

