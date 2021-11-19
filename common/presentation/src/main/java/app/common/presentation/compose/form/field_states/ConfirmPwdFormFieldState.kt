package app.common.presentation.compose.form.field_states

import androidx.compose.runtime.MutableState
import app.common.core.app.CoreApp
import app.common.presentation.R
import app.common.presentation.compose.form.FormFieldState
import app.common.presentation.compose.form.validator.InlineValidator

//TODO: replace Confirm Pwd in Login,Register Screens
class ConfirmPwdFormFieldState(
    private val newPwd: MutableState<String>
) : FormFieldState(
    validator = InlineValidator { value ->
        if (value.isEmpty() || value.isBlank())
            return@InlineValidator false
        return@InlineValidator value == newPwd.value
    },
    errorFor = {
        validate(value = it, newPwd = newPwd)
    }
)

fun validate(value: String, newPwd: MutableState<String>): String {
    if (newPwd.value.isEmpty() || newPwd.value.isBlank())
        return CoreApp.string(R.string.required)
    if (value != newPwd.value) {
        return CoreApp.string(R.string.passwords_not_matching)
    }
    return CoreApp.string(R.string.required)
}