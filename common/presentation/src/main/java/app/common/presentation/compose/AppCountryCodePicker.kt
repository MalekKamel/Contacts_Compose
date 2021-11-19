package app.common.presentation.compose

import android.content.Context
import android.view.LayoutInflater
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import app.common.presentation.R
import com.hbb20.CountryCodePicker

@Composable
fun AppCountryCodePicker(
    context: Context = LocalContext.current,
    onFocusChanged: (FocusState) -> Unit = {},
    modifier: Modifier = Modifier
        .padding(top = 30.dp, start = 65.dp)
        .onFocusChanged(onFocusChanged),
    showFlag: Boolean = true,
    showDialogFlag: Boolean = true,
    showArrow: Boolean = true,
    showPhoneCode: Boolean = true,
    onValueChanged: (String) -> Unit,
) {
    val view = LayoutInflater.from(context).inflate(
        R.layout.code_country_picker,
        null
    )
    val codePicker = view.findViewById<CountryCodePicker>(R.id.codePicker)

    codePicker.setOnCountryChangeListener {
        onValueChanged.invoke(codePicker.selectedCountryCodeWithPlus)
    }

    codePicker.showFlag(showFlag)
    codePicker.showArrow(showArrow)
    codePicker.ccpDialogShowFlag = showDialogFlag
    codePicker.setShowPhoneCode(showPhoneCode)

    //last cashed value
    val lastSelection = codePicker.selectedCountryCodeWithPlus
    onValueChanged(lastSelection.toString())

    modifier.onFocusChanged(onFocusChanged)
    AndroidView(
        factory = { view },
        modifier = modifier
    ) {}
}