package app.common.core.extension

import android.content.Context


fun String.withTitle(title: String): String {
    return "$title: $this"
}

fun String?.withTitleRes(title: Int, context: Context): String {
    return "${context.getString(title)}: $this"
}
