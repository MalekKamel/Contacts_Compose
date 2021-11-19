package app.common.presentation.compose.form.validator

import androidx.compose.runtime.MutableState
import app.common.core.app.CoreApp
import app.common.presentation.compose.form.TextFieldState
import com.sha.formvalidator.textview.validator.TextValidator
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil

class PhoneValidator(
    private val disableState: TextFieldState? = null,
    private val countryCode: MutableState<String>,
    errorMessage: String = ""
) :
    TextValidator(errorMessage) {

    override fun isValid(text: String): Boolean {
        disableState?.let {
            if (it.isFocused) return true
        }
        if (text.isEmpty() || text.isBlank())
            return false
        val fullNumber = "${countryCode.value}$text"
        val phoneNumUtil = PhoneNumberUtil.createInstance(CoreApp.context)
        return try {
            val phoneNumber = phoneNumUtil.parse(
                fullNumber,
                ""
            )
            phoneNumUtil.isValidNumber(phoneNumber)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
