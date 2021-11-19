package app.common.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.common.presentation.R
import app.common.presentation.compose.form.AppFormTextField
import app.common.presentation.compose.form.TextFieldState

//TODO: Localize
@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    labelSearch: String = "16 Elmoltaqa ..",
    iconRes: Int = R.drawable.heart_icon
) {
    Box(
        Modifier
            .height(100.dp)
            .fillMaxWidth()
    ) {
        AppFormTextField(
            state =
            TextFieldState(validator = { false },
                errorFor = { "" }),
            placeholder = labelSearch
        )

        if (iconRes <= -1)
            return

        Image(
            modifier = Modifier
                .height(70.dp)
                .width(70.dp)
                .align(Alignment.CenterEnd)
                .padding(end = 13.dp, bottom = 10.dp),
            painter = painterResource(id = iconRes),
            contentDescription = "Search Icon"
        )
    }
}