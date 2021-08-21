package app.common.core.extension

import android.content.Context


fun String.withSuffix(suffix: String): String {
    return "$this $suffix"
}

fun String.withSuffixRes(suffix: Int, context: Context): String {
    return "$this ${context.getString(suffix)}"
}

