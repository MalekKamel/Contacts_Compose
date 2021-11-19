package app.common.presentation.compose

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import app.common.presentation.R
import com.goodiebag.pinview.Pinview

const val pinViewLength = 6

@Composable
fun AppPinView(
    context: Context,
    pinLengthValue: Int = pinViewLength,
    pinWidthValue: Int = 120,
    pinHeightValue: Int = 130,
    modifier: Modifier = Modifier,
    onComplete: (String) -> Unit
) {

    val view = LayoutInflater.from(context)
        .inflate(R.layout.layout_pin_view, null, false)
    val pinView = view.findViewById<Pinview>(R.id.pinView)

    pinView.apply {
        pinLength = pinLengthValue
        pinHeight = pinHeightValue
        pinWidth = pinWidthValue
    }

    pinView.setPinViewEventListener { pin, _ ->
        if (pin.value.length >= pinViewLength) {
            onComplete(pin.value)
        }
    }

    if (pinView.parent != null)
        (pinView.parent as ViewGroup).removeView(pinView)

    AndroidView(
        factory = { pinView },
        modifier = modifier
    ) {}
}

