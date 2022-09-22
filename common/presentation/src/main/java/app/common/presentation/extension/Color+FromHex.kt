package app.common.presentation.extension

import androidx.compose.ui.graphics.Color

fun String.hexColor(): Color {
    val color = android.graphics.Color.parseColor(this)
    return Color(color)
}