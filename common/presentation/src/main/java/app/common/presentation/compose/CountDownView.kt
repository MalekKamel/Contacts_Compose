package app.common.presentation.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.common.core.countdown.AppCountDown
import app.common.presentation.compose.theme.AppColor
import app.common.presentation.compose.theme.Roboto
import org.threeten.bp.Duration


class CountDownView {
    var text = mutableStateOf("")
    var isActive = mutableStateOf(false)

    private val timer: AppCountDown by lazy {
        AppCountDown.create(Duration.ofSeconds(60)) {
            onTick = { output ->
                showTime(output)
            }
            onFinish = {
                this@CountDownView.cancel()
            }
        }
    }

    private fun showTime(output: AppCountDown.Output) {
        text.value = if (output.minuteSecondText == "00:00") "" else output.minuteSecondText
    }

    @Composable
    fun Content() {
        Text(
            text = text.value,
            color = AppColor.dark_indigo,
            fontWeight = FontWeight.Bold,
            fontFamily = Roboto,
            fontSize = 14.sp
        )
    }

    fun start() {
        isActive.value = true
        timer.start()
    }

    fun cancel() {
        isActive.value = false
        timer.cancel()
        text.value = ""
    }

}