package app.common.presentation.compose

import android.widget.TextView
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

// TODO: to be improved
@Composable
fun TextHtmlStyled(
    beforeText: String,
    boldText: String,
    afterText: String = ""
) {

    val txtHtml = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "</head>\n" +
            "<body>\n" +
            "    <p style=\"font-size: 50px;\"> $beforeText  <b>$boldText</b>  $afterText</p>\n" +
            "</body>"

    val textSpanned = HtmlCompat.fromHtml(
        txtHtml,
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )

    AndroidView(
        factory = { context ->
            TextView(context).apply {
                text = textSpanned
                textSize = 18f
            }
        }, modifier = Modifier.padding(
            start = 15.dp,
            end = 15.dp
        )
    )
}
