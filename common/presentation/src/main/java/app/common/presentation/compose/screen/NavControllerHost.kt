package app.common.presentation.compose.screen

import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController

interface NavControllerHost {
    val activity: FragmentActivity
    val navController: NavHostController

    @Composable
    fun Setup()

    fun navigate(route: String) {
        navController.navigate(route)
    }
    fun navigate(route: ScreenRoute) = navigate(route.route)

    fun popBackStack() = navController.popBackStack()

    fun putArguments(block: (SavedStateHandle?) -> Unit) {
        navController.currentBackStackEntry?.savedStateHandle.apply {
            block(this)
        }
    }

    fun <T> getArgument(key: String): T? {
        return navController.previousBackStackEntry
            ?.arguments?.getParcelable(key)
    }

}