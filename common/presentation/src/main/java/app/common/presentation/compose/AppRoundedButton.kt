package app.common.presentation.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.common.presentation.compose.theme.AppColor
import app.common.presentation.compose.theme.Roboto

@Composable
fun AppRoundedButton(
    text: String,
    color: Color = AppColor.dark_indigo,
    textColor: Color = Color.White,
    borderStrokeColor: Color = Color.Transparent,
    horizontalPadding: Dp = 16.dp,
    modifier: Modifier = modifierBtn(color, horizontalPadding),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors
            (backgroundColor = color),
        border = BorderStroke(2.dp, color = borderStrokeColor)
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontSize = 18.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)

        )
    }
}

fun modifierBtn(color: Color, horizontalPadding: Dp): Modifier {
    return Modifier
        .fillMaxWidth()
        .height(56.dp)
        .padding(horizontal = horizontalPadding)
        .background(
            color = color,
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        )
}