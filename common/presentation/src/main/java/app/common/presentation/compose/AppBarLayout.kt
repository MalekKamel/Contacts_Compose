package app.common.presentation.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.common.presentation.compose.theme.AppColor

@Composable
fun AppBarLayout(
    params: AppBarLayoutParams.Builder.() -> Unit = {},
    headerImage: @Composable () -> Unit = {},
    disMissView: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    val options = AppBarLayoutParams.Builder()
        .apply(params)
        .build()

    Box(
        modifier = Modifier
            .background(AppColor.light_periwinkle)
            .fillMaxSize()
    ) {

        AppBarHeaderView(options = options)
        AppBarRoundedCornersCard(content, options = options)
        headerImage()
        disMissView()
    }

}


@Composable
fun AppBarRoundedCornersCard(
    content: @Composable () -> Unit,
    options: AppBarLayoutParams
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 245.dp
            ),
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp
        ),
        elevation = 15.dp
    ) {
        var modifier = Modifier.fillMaxWidth()

        if (options.isContentScrollable) {
            val stateScroll = rememberScrollState()
            modifier = modifier.then(Modifier.verticalScroll(stateScroll))
        }

        Box(modifier = modifier) {
            content()
        }

    }
}

@Composable
fun AppBarHeaderView(options: AppBarLayoutParams) {
    val modifier = Modifier
        .background(AppColor.light_periwinkle)
        .fillMaxSize()
        .fillMaxWidth()

    Box(modifier) {
        Text(
            text = options.title,
            modifier = Modifier
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = TextStyle(fontWeight = FontWeight.Normal),
            fontSize = 20.sp
        )

    }
}

@Composable
fun ClickableImage(
    @DrawableRes imageRes: Int,
    width: Dp = Dp.Unspecified,
    height: Dp = Dp.Unspecified,
    onClick: () -> Unit
) {
    Box(Modifier.padding(start = 25.dp, top = 25.dp)) {

        Image(
            modifier = Modifier
                .clickable { onClick() }
                .width(width)
                .height(height),
            painter = painterResource(id = imageRes),
            contentDescription = ""
        )
    }
}

data class AppBarLayoutParams(
    var title: String = "",
    var isContentScrollable: Boolean = false,
) {
    class Builder {
        private val params = AppBarLayoutParams()

        fun title(value: String) {
            params.title = value
        }

        fun isContentScrollable(value: Boolean) {
            params.isContentScrollable = value
        }

        fun build(): AppBarLayoutParams {
            return params
        }
    }
}