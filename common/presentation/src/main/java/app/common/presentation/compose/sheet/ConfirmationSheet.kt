package app.common.presentation.compose.sheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.common.presentation.compose.AppRoundedButton
import app.common.presentation.compose.theme.AppColor
import app.common.presentation.compose.theme.Roboto

class ConfirmationSheet(
    block: ConfirmationSheet.() -> Unit,
    inline val onSubmit: () -> Unit
) : AppBottomSheetDialog() {
    var title: String = ""
    var subtilte: String = ""
    var description: String = ""

    init {
        apply(block)
    }

    @Composable
    override fun Content() {
        Surface(color = Color.Black.copy(alpha = 0.6f)) {
            Card(
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                ),
                elevation = 20.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    HeaderView()

                    Spacer(modifier = Modifier.height(25.dp))

                    Divider(
                        modifier = Modifier
                            .height(5.dp)
                            .padding(2.dp),
                        color = AppColor.gray_light
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Footer()

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }

    @Composable
    private fun HeaderView() {
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontFamily= Roboto ,
                color = AppColor.dark_indigo_3,
                fontSize = 21.sp
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = subtilte,
            color = AppColor.black,
            fontSize = 16.sp,
            fontFamily= Roboto ,
            textAlign = TextAlign.Center

        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            modifier = Modifier.alpha(.5f),
            text = description,
            color = AppColor.black,
            fontSize = 15.sp,
        )
    }

    @Composable
    private fun Footer() {

        Row(
            Modifier.fillMaxWidth()
        ) {
            AppRoundedButton(
                text = stringResource(id = app.common.presentation.R.string.yes),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                onSubmit()
            }

            Spacer(modifier = Modifier.width(10.dp))

            AppRoundedButton(
                text = stringResource(id = app.common.presentation.R.string.discard),
                color = Color.White,
                textColor = AppColor.dark_indigo,
                borderStrokeColor = AppColor.dark_indigo,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)

            ) {
                dismiss()
            }
        }
    }
}