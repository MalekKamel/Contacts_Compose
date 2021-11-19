package app.common.presentation.compose.form

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.common.presentation.compose.theme.AppColor
import app.common.presentation.compose.theme.Roboto

@Composable
fun FormTextField(
        state: TextFieldState,
        modifier: Modifier = Modifier,
        label: @Composable (() -> Unit)? = null,
        enabled: Boolean = true,
        readOnly: Boolean = false,
        textStyle: TextStyle,
        placeholder: @Composable (() -> Unit)? = null,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        singleLine: Boolean = false,
        maxLines: Int = Int.MAX_VALUE,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        shape: Shape = MaterialTheme.shapes.small,
        colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
        color: Color ,
        showErrorView: ((String?) -> Unit)? = null
) {
    Column {
        OutlinedTextField(
                value = state.value,
                onValueChange = {
                    state.value = it
                    state.enableShowErrors()
                },
                modifier = modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            state.onFocusChange(focusState.isFocused)
                            if (!focusState.isFocused) {
                                state.enableShowErrors()
                            }
                        },
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle,
                label = label,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                isError = state.showErrors(),
                visualTransformation = visualTransformation,
                singleLine = singleLine,
                maxLines = maxLines,
                interactionSource = interactionSource,
                shape = shape,
                colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = color,
                        cursorColor = Color.Black,
                        errorCursorColor = AppColor.scarlet,
                        focusedIndicatorColor = AppColor.dark_indigo,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedLabelColor = Color.Transparent,
                        disabledLabelColor = Color.Transparent,
                        errorIndicatorColor = AppColor.scarlet,
                ),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
        )

        val error = state.getError()

        if (showErrorView != null) {
            showErrorView(error)
        } else {
            error?.let { TextFieldError(textError = it) }
        }
    }
}

@Composable
fun TextFieldError(textError: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
                text = textError,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                style = TextStyle(color = AppColor.scarlet, fontSize = 14.sp, fontFamily = Roboto)
        )
    }
}
