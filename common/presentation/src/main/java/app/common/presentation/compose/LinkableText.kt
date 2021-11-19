package app.common.presentation.compose

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun LinkableText(
    txtLogin: String,
    color: Color,
    onClick: () -> Unit
) {
    ClickableText(
        text = createHyperLink(txtLogin, color),
        onClick = {
            onClick.invoke()
        })
}

private fun createHyperLink(txtLogin: String, color: Color): AnnotatedString {
    return buildAnnotatedString {
        pushStyle(
            style = SpanStyle(
                color = color,
                textDecoration = TextDecoration.Underline
            )
        )
        append(txtLogin)
        addStringAnnotation(
            tag = "URL",
            annotation = "https://google.com", // TODO: why this constant?!
            start = 0,
            end = txtLogin.length
        )
    }
}