package app.common.core.date

enum class AmPm(val value: String) {
    AM("am"),
    PM("pm");

    companion object{
        fun from(value: String): AmPm? {
            return when (value) {
                PM.name -> PM
                AM.name -> AM
                else -> null
            }
        }
    }
}

