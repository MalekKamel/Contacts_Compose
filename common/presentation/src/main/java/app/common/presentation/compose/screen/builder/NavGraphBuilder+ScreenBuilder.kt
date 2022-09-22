package app.common.presentation.compose.screen.builder

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.common.presentation.compose.screen.ScreenRoute


/**
 * This func is responsible to build nav screen by 'ScreenRoute'
 */
fun NavGraphBuilder.screenBuilder(
    screen: ScreenRoute,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = screen.route,
        arguments = arguments
    ) {
        content(it)
    }
}