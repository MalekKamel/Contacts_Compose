//package app.common.presentation.compose.form
//
//import androidx.annotation.DrawableRes
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.onFocusChanged
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun AppOutlinedFormTextField(
//    state: TextFieldState,
//    label: String,
//    @DrawableRes leadingIconRes: Int,
//    keyboardType: KeyboardType = KeyboardType.Text,
//    modifier: Modifier = appTextFieldModifier(),
//    enabled: Boolean = true,
//    readOnly: Boolean = false,
//    textStyle: TextStyle = LocalTextStyle.current,
//    placeholder: @Composable (() -> Unit)? = null,
//    trailingIcon: @Composable (() -> Unit)? = null,
//    visualTransformation: VisualTransformation = VisualTransformation.None,
//    singleLine: Boolean = false,
//    maxLines: Int = Int.MAX_VALUE,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    shape: Shape = MaterialTheme.shapes.small,
//    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
//    onImeAction: () -> Unit = {},
//) {
//    FormTextField(
//        state = state,
//        modifier = modifier
//            .fillMaxWidth()
//            .onFocusChanged { focusState ->
//                state.onFocusChange(focusState.isFocused)
//                if (!focusState.isFocused) {
//                    state.enableShowErrors()
//                }
//            },
//        label = {
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Text(
//                    text = label,
//                    style = MaterialTheme.typography.body2
//                )
//            }
//        },
//        enabled = enabled,
//        readOnly = readOnly,
//        textStyle = textStyle,
//        placeholder = placeholder,
//        leadingIcon = {
//            Image(
//                painter = painterResource(id = leadingIconRes),
//                contentDescription = "$label Icon",
//                Modifier.padding(4.dp)
//            )
//        },
//        trailingIcon = trailingIcon,
//        visualTransformation = visualTransformation,
//        singleLine = singleLine,
//        maxLines = maxLines,
//        interactionSource = interactionSource,
//        shape = shape,
//        colors = colors,
//        keyboardOptions = KeyboardOptions(
//            keyboardType = keyboardType,
//            imeAction = ImeAction.Next,
//        ),
//        keyboardActions = KeyboardActions(
//            onDone = {
//                onImeAction()
//            }
//        )
//    )
//}
//
//@Composable
//fun appTextFieldModifier(): Modifier {
//    return Modifier
//        .fillMaxWidth()
//        .padding(
//            top = 16.dp,
//            start = 16.dp,
//            end = 16.dp
//        )
//        .height(48.dp)
//}
