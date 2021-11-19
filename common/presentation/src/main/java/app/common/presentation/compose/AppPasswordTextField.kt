package app.common.presentation.compose

import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import app.common.presentation.R
import app.common.presentation.compose.form.AppFormTextField
import app.common.presentation.compose.form.FormFieldState
import app.common.presentation.compose.theme.AppColor


@Composable
fun AppPasswordTextField(
    value: FormFieldState,
    placeholder: String,
    imeAction: ImeAction = ImeAction.Done,
) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val passwordVisibilityIcon = if (passwordVisibility)
        R.drawable.design_ic_visibility
    else
        R.drawable.design_ic_visibility_off

    AppFormTextField(
        state = value,
        placeholder = placeholder,
        imeAction = imeAction,
        visualTransformation = if (passwordVisibility) VisualTransformation.None
        else
            PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { passwordVisibility = !passwordVisibility },
                Modifier.height(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = passwordVisibilityIcon),
                    contentDescription = "Password visibility button",
                    tint = if (!passwordVisibility) AppColor.gray else AppColor.dark_indigo)
            }
        },
    )

}

