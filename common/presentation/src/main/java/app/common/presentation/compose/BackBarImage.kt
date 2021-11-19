package app.common.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import app.common.presentation.R

@Composable
fun ArrowDismissImage(onClick: () -> Unit) {
    ClickableImage(
        imageRes = R.drawable.ic_back_icon,
        width = 25.dp,
        height = 25.dp,
    ) {
        onClick()
    }
}

@Composable
fun ExitDismissImage(onClick: () -> Unit) {
    ClickableImage(
        imageRes = R.drawable.ic_close,
        width = 15.dp,
        height = 15.dp,
    ) {
        onClick()
    }
}
