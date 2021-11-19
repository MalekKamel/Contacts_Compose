package app.common.presentation.compose.form.validator

import app.common.core.app.CoreApp
import app.common.presentation.R
import com.sha.formvalidator.textview.validator.pattern.PatternValidator

class PasswordValidator : PatternValidator(
    errorMessage = CoreApp.string(R.string.pwd_msg_validation),
    regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$"
)