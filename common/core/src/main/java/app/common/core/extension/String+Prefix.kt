package app.common.core.extension

import android.content.Context


fun String.withPrefix(suffix: String): String {
    return "$suffix $this"
}

fun String.withPrefixRes(suffix: Int, context: Context): String {
    return "${context.getString(suffix)} $this"
}