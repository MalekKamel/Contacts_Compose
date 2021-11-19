package app.common.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.common.presentation.compose.theme.AppColor

@Composable
fun BarView(
    color: Color = AppColor.dark_indigo_1,
    width: Dp = 40.dp,
    height: Dp = 3.dp
) {
    Card(
        modifier = Modifier
            .width(width)
            .background(Color.Transparent)
            .height(height),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = color
    ) {}

}