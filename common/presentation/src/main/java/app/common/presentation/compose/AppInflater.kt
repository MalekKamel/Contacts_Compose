package app.common.presentation.compose

import android.content.Context
import android.view.LayoutInflater
import android.view.View


fun Int.view(
    context: Context,
    attachToRoot: Boolean = false
): View? {
    return LayoutInflater.from(context).inflate(
        this,
        null,
        attachToRoot
    )
}