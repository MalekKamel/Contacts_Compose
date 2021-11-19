package app.common.presentation.compose.form

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.common.presentation.compose.theme.AppColor
import app.common.presentation.compose.theme.Roboto

@Composable
fun AppFormTextField(
        state: TextFieldState,
        keyboardType: KeyboardType = KeyboardType.Text,
        onFocusChanged: (FocusState) -> Unit = {},
        modifier: Modifier = AppTextFieldModifier(onFocusChanged),
        enabled: Boolean = true,
        readOnly: Boolean = false,
        placeholder: String,
        trailingIcon: @Composable (() -> Unit)? = null,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        singleLine: Boolean = true,
        maxLines: Int = 1,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        shape: Shape = RoundedCornerShape(15.dp),
        color: Color = AppColor.alabaster,
        imeAction: ImeAction = ImeAction.Next,
        showErrorView: ((String?) -> Unit)? = null
) {
    val localFocusManager = LocalFocusManager.current
    FormTextField(
            state = state,
            modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(shape = shape, color = color)
                    .onFocusChanged { focusState ->
                        state.onFocusChange(focusState.isFocused)
                        if (!focusState.isFocused) {
                            state.enableShowErrors()
                        }
                        onFocusChanged(focusState)
                    },

            enabled = enabled,
            readOnly = readOnly,
            textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = Roboto,
                    textAlign = TextAlign.Start,
                    lineHeight = TextUnit.Unspecified
            ),
            placeholder = {
                Text(
                        text = placeholder,
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Light,
                        color = AppColor.gray
                )
            },
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            singleLine = singleLine,
            maxLines = maxLines,
            interactionSource = interactionSource,
            keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction,
            ),
            keyboardActions = KeyboardActions(
                    onDone = {
                        localFocusManager.clearFocus()
                    },
                    onNext = {
                        localFocusManager.moveFocus(FocusDirection.Down)
                    }
            ),
            showErrorView = showErrorView,
            color = color
    )
}

@Composable
fun AppTextFieldModifier(onFocusChanged: (FocusState) -> Unit = {}): Modifier {
    return Modifier
            .fillMaxWidth()
            .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
            )
            .height(56.dp)
            .onFocusChanged(onFocusChanged)
}



