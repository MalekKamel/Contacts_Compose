package app.common.presentation.compose.sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.common.presentation.R
import app.common.presentation.compose.theme.AppColor
import app.common.presentation.compose.theme.Roboto

class SuccessBottomSheetDialog(private val args: Args) : AppBottomSheetDialog() {

    data class Args(
        var icon: Int = R.drawable.success_ic,
        var title: String,
    )

    @Composable
    override fun Content() {
        Surface(color = Color.Black.copy(alpha = 0.6f)) {
            Card(
                modifier = Modifier,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                ),
                elevation = 20.dp
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SuccessView()
                }
            }
        }

    }

    @Composable
    fun SuccessView() {
        Spacer(modifier = Modifier.height(60.dp))

        Image(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp),
            painter = painterResource(id = args.icon),
            contentDescription = args.title
        )

        Spacer(modifier = Modifier.height(13.dp))

        Text(
            text = args.title,
            color = AppColor.warm_grey,
            fontSize = 18.sp,
            fontFamily = Roboto
        )

        Spacer(modifier = Modifier.height(50.dp))

    }

}