package app.common.presentation.compose.form.field_states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.common.core.app.CoreApp
import app.common.presentation.R
import app.common.presentation.compose.form.TextFieldState
import com.sha.formvalidator.textview.validator.RequiredValidator
import com.sha.formvalidator.textview.validator.TextValidator

class PhoneFormFieldState(
    private val validator: TextValidator = RequiredValidator(),
    var errorMessage: (String) -> String = { CoreApp.string(R.string.required) }
) : TextFieldState(
    errorFor = errorMessage
) {

    //Code Country Validation with Number
    var validWithCodeCountry: Boolean by mutableStateOf(true)

    override val isValid: Boolean
        get() = isNumberValid()

    private fun isNumberValid(): Boolean {
        if (value.isBlank() || value.isEmpty())
            return false

        if (!validWithCodeCountry) {
            errorFor = { CoreApp.string(R.string.not_valid_mobile) }
            return false
        }

        return validator.isValid(value)
    }

}