package app.common.presentation.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import app.common.presentation.compose.theme.AppColor
import app.common.presentation.compose.theme.Roboto

// TODO: localize all strings
@Composable
fun TitleSubtitle(title: String, subtitle: String, subTextAlign: TextAlign = TextAlign.Start) {
    // TODO: use Column instead of Row
    Row {
        Text(text = title,
                color = AppColor.black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Roboto
        )
    }
    Row() {
        Text(
                text = subtitle,
                color = AppColor.black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                fontFamily = Roboto,
                textAlign = subTextAlign
        )
    }
}