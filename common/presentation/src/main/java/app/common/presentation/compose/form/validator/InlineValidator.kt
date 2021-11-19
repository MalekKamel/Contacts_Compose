package app.common.presentation.compose.form.validator

import app.common.core.app.CoreApp
import app.common.presentation.R
import com.sha.formvalidator.textview.validator.TextValidator

class InlineValidator(private val validate: (String) -> Boolean) : TextValidator(
    errorMessage = CoreApp.string(R.string.required)
) {
    override fun isValid(text: String): Boolean {
        return validate(text)
    }
}