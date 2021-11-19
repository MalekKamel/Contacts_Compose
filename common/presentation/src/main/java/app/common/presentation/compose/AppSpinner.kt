package app.common.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.common.presentation.R
import app.common.presentation.compose.theme.AppColor
import app.common.presentation.compose.theme.Roboto

class AppSpinner(
    val items: List<SpinnerItem>,
    defaultSelectedItem: SpinnerItem = items[0],
) {
    data class SpinnerItem(
        val title: String = "",
        val text: String,
        val type: String //TODO: replace with Enum Type
    )

    private var selectedItem: SpinnerItem by mutableStateOf(defaultSelectedItem)
    private var expanded: Boolean by mutableStateOf(false)
    private var iconResExpand: Int by mutableStateOf(R.drawable.arrow_bottom_icon)
    private lateinit var onItemSelected: (SpinnerItem) -> Unit

    init {
        val item = items.findLast { it.title != "" }
        if (item != null)
            selectedItem = item
    }

    @Composable
    fun Setup(onItemSelected: (SpinnerItem) -> Unit) {
        this.onItemSelected = onItemSelected
        SpinnerView()
        Row(
            Modifier.offset((-2).dp),
        ) {
            MaterialTheme(
                shapes =
                MaterialTheme.shapes.copy(
                    medium = RoundedCornerShape(
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    DropDownMenuView()
                }
            }
        }
    }

    @Composable
    private fun DropDownMenuView() {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                updateExpandState(false)
            },
            Modifier
                .wrapContentSize(Alignment.Center)
                .widthIn(min = 105.dp, max = 120.dp),
        ) {
            BindItems()
        }

    }

    @Composable
    private fun BindItems() {
        items.forEach { item ->
            DropdownMenuItem(
                onClick = {
                    selectedItem = item
                    updateExpandState(false)
                    //invoke on Selection
                    onItemSelected(selectedItem)
                },
            ) {
                val itemStyle = style(item = item)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = item.text,
                        style = itemStyle,
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp,
                        maxLines = 1
                    )
                }

            }
        }
    }

    private fun getItemTitle(): String {
        if (selectedItem.title.isNotEmpty()) {
            return selectedItem.title
        }
        return selectedItem.text
    }

    @Composable
    private fun SpinnerView() {
        Button(
            onClick = {
                updateExpandState(true)
            },
            modifier = Modifier
                .background(
                    color = AppColor.dark_indigo,
                    shape = RoundedCornerShape(corner = CornerSize(18.dp)),
                )
                .height(45.dp)
                .widthIn(min = 105.dp, max = 120.dp),
            colors = ButtonDefaults.buttonColors
                (backgroundColor = AppColor.dark_indigo),
            shape = RoundedCornerShape(18.dp),
        ) {
            Column(
                Modifier.weight(.8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = getItemTitle(),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    fontFamily = Roboto,
                    maxLines = 1
                )
            }
            Column(
                Modifier.weight(.2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(id = iconResExpand),
                    contentDescription = stringResource(id = R.string.drop_down_list),
                    tint = Color.White,
                )
            }

        }
    }

    @Composable
    private fun style(item: SpinnerItem): TextStyle {
        val isSelected = item == selectedItem
        return if (isSelected) {
            MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold,
                color = AppColor.dark_indigo,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontFamily = Roboto,
            )
        } else {
            MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Normal,
                color = AppColor.gray,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontFamily = Roboto,
            )
        }
    }

    private fun updateExpandState(value: Boolean) {
        expanded = value

        iconResExpand = if (expanded)
            R.drawable.arrow_top_icon
        else
            R.drawable.arrow_bottom_icon

    }

    fun selectedItem() = selectedItem
}
