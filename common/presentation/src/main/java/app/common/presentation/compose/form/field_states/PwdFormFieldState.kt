package app.common.presentation.compose.form.field_states

import app.common.core.app.CoreApp
import app.common.presentation.R
import app.common.presentation.compose.form.FormFieldState
import app.common.presentation.compose.form.validator.PasswordValidator

class PwdFormFieldState : FormFieldState(
    validator = PasswordValidator(),
    errorFor = {

        when {
            it.isEmpty() || it.isBlank() -> CoreApp.string(R.string.required)
            else -> CoreApp.string(R.string.pwd_msg_validation)
        }

    }
)