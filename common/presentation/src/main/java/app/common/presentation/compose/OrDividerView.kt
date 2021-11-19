package app.common.presentation.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.common.presentation.compose.theme.AppColor
import app.common.presentation.compose.theme.Roboto

@Composable
fun OrDividerView() {
    Spacer(modifier = Modifier.height(20.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        DividerLine()

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "or",
            fontSize = 14.sp,
            color = AppColor.black,
            fontFamily = Roboto,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(8.dp))

        DividerLine()
    }
    Spacer(modifier = Modifier.height(4.dp))


}

@Composable
fun DividerLine() {
    Divider(
        color = AppColor.pinkish_grey, modifier = Modifier
            .height(1.dp)
            .padding(start = 16.dp, end = 16.dp)
            .width(120.dp)

    )
}