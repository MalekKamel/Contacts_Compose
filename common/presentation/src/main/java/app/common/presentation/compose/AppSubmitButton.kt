package app.common.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.common.presentation.compose.theme.AppColor

@Composable
fun AppSubmitButton(
    text: String,
    onClick: () -> Unit
) {
    val gradient = Brush.verticalGradient(
        listOf(AppColor.light_navy, AppColor.royal)
    )

    Button(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(start = 20.dp, end = 20.dp),
        shape = RoundedCornerShape(20),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            onClick.invoke()
        }) {
        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .background(brush = gradient)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(top = 10.dp)  //todo remove Padding And Center TExt
                .fillMaxHeight(),
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            text = text
        )
    }
}