package app.common.presentation.compose

import android.content.Context
import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun Int.toCompose(context: Context) {
    val view = LayoutInflater.from(context).inflate(this, null)
    AndroidView(factory = { view }, modifier = Modifier) {
    }
}