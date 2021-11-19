package app.common.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.common.presentation.compose.theme.AppColor

@Composable
fun AppShape(
    shape: Shape,
    size: Dp = 12.dp,
    color: Color = AppColor.dark_indigo_2,
    topPadding: Dp = 0.dp,
    modifier: Modifier = shapeModifier(topPadding),
) {

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(shape)
                .background(color),
        )
    }
}

fun shapeModifier(topPadding: Dp = 0.dp) = Modifier
    .fillMaxWidth()
    .wrapContentSize(Alignment.Center)
    .padding(top = topPadding)