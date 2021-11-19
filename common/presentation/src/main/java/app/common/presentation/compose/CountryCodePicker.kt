package app.common.presentation.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import app.common.presentation.R
import app.common.presentation.compose.form.AppFormTextField
import app.common.presentation.compose.form.FormFieldState
import app.common.presentation.compose.form.TextFieldError
import app.common.presentation.compose.theme.AppColor

// TODO: rename to AppPhoneField
@Composable
fun AppCountryCodeField(
    value: FormFieldState,
    onCountryCodeChanged: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
) {
    var errorState: String? by remember { mutableStateOf(null) }

    Column {
        Content(
            value = value,
            onCountryCodeChanged = onCountryCodeChanged,
            onFocusChanged = onFocusChanged,
            imeAction = imeAction,
            showErrorView = { error ->
                errorState = error
            }
        )

        errorState?.let { TextFieldError(textError = it) }
    }

}

@Composable
private fun Content(
    value: FormFieldState,
    onCountryCodeChanged: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    showErrorView: ((String?) -> Unit)? = null
) {
    val heightField = 56.dp
    Row(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {

        AppCountryCodePicker(
            context = LocalContext.current,
            showPhoneCode = false,
            showArrow = true,
            onFocusChanged = onFocusChanged,
            modifier = Modifier
                .height(heightField)
                .background(
                    color = AppColor.alabaster,
                    shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                )
                .onFocusChanged(onFocusChanged),
        ) {
            val valueCode = it
            Log.d("valueCode", valueCode)
            onCountryCodeChanged.invoke(it)
        }

        AppFormTextField(
            modifier = Modifier
                .height(heightField)
                .onFocusChanged(onFocusChanged),
            state = value,
            placeholder = stringResource(id = R.string.mobile_number),
            singleLine = true,
            keyboardType = KeyboardType.Phone,
            shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
            imeAction = imeAction,
            showErrorView = showErrorView

        )
    }
}

