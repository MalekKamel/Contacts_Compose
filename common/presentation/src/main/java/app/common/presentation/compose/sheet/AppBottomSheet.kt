package app.common.presentation.compose.sheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
class AppBottomSheet constructor(private val scaffoldState: BottomSheetScaffoldState) {
    private val halfPeek = 360.dp
    private val collapsePeek = 170.dp
    private val peek = mutableStateOf(collapsePeek)
    private var isSwiping = false
    private val expandPeek = Dp.Infinity
    private val isHalfHeight
        get() = peek.value == halfPeek

    private var isSettled = true

    private fun updatePeek(
        current: BottomSheetValue,
        targetValue: BottomSheetValue,
        fraction: Float
    ) {
        if (fraction != 1.0f) return
        val isStateChanged = current != targetValue

        when (current) {
            BottomSheetValue.Collapsed -> {
                if (!isStateChanged) return

                if (isHalfHeight) {
                    if (peek.value == collapsePeek) return
                    peek.value = collapsePeek
                    return
                }
                if (peek.value == halfPeek) return
                peek.value = halfPeek
            }
            BottomSheetValue.Expanded -> {
                if (!isStateChanged) return
                if (isHalfHeight) {
                    if (peek.value == expandPeek) return
                    peek.value = expandPeek
                    return
                }
                if (peek.value == halfPeek) return
                peek.value = halfPeek
            }
        }
    }

    @ExperimentalMaterialApi
    @Composable
    fun Content(
        topBarView: @Composable () -> Unit = {},
        drawerContent: @Composable () -> Unit = {},
        floatingView: @Composable () -> Unit = {},
        sheetContent: @Composable ColumnScope.() -> Unit,
        screenContent: @Composable () -> Unit
    ) {
        val sheetCornersShape =
            if (scaffoldState.bottomSheetState.isCollapsed ||
                scaffoldState.bottomSheetState.isAnimationRunning
            )
                20.dp else 0.dp

        // TODO: implement
//        val fraction = scaffoldState.bottomSheetState.progress.fraction
//        val stateFrom = scaffoldState.bottomSheetState.progress.from
//        val targetValue = scaffoldState.bottomSheetState.targetValue
//        val currentValue = scaffoldState.bottomSheetState.currentValue
//
//        if (scaffoldState.bottomSheetState.isAnimationRunning) {
//            updatePeek(
//                currentValue,
//                targetValue,
//                fraction
//            )
//        }

        BottomSheetScaffold(
            sheetContent = {
                sheetContent()
            },
            floatingActionButton = {
                floatingView()
            },
            scaffoldState = scaffoldState,
            topBar = {
                topBarView()
            },
            sheetPeekHeight = peek.value,
            sheetShape = RoundedCornerShape(
                topStart = sheetCornersShape,
                topEnd = sheetCornersShape
            ),
            sheetElevation = 20.dp,
            drawerScrimColor = Color.White,
//        drawerContent = {
//            drawerContent()
//        },
//        drawerGesturesEnabled = true
        ) {
            screenContent()
        }
    }

    fun updatePeek(value: Dp): AppBottomSheet {
        peek.value = value
        return this
    }
}


@ExperimentalMaterialApi
@Composable
fun ModalBottomSheet(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded)

    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            //. sheetContent
        }
    ) {
        val coroutineScope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "TopAppBar")
                    }
                )
            },

            content = { innerPadding ->
                //...main content
            }
        )
    }
}


@ExperimentalMaterialApi
@Composable
fun SideMenuContent(scope: CoroutineScope, scaffoldState: BottomSheetScaffoldState) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Drawer content")
        Spacer(Modifier.height(20.dp))
        Button(onClick = { scope.launch { scaffoldState.drawerState.close() } }) {
            Text("Close drawer")
        }
    }
}


//TODO: remove after test
@ExperimentalMaterialApi
@Composable
fun BottomSheet(scaffoldState: ScaffoldState) {
    val coroutineScope = rememberCoroutineScope()
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            //. sheetContent
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "TopAppBar")
                    }
                )
            },
            bottomBar = {
                BottomAppBar(modifier = Modifier) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch { bottomState.show() }
                        }
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                    }
                }
            },

            content = { innerPadding ->
                //...main content
            }
        )
    }
}


