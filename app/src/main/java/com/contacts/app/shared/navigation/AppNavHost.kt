package com.contacts.app.shared.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import app.common.presentation.compose.screen.NavControllerHost
import app.common.presentation.compose.screen.builder.screenBuilder
import com.contacts.app.shared.screen.Screen
import com.contacts.app.ui.home.HomeScreen
import com.contacts.app.ui.splash.SplashScreen

class AppNavHost(
    override val activity: FragmentActivity,
    override val navController: NavHostController
) : NavControllerHost {

    @Composable
    override fun Setup() {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Screen.Splash.route
        ) {
            screenBuilder(Screen.Splash) {
                remember {
                    SplashScreen(this@AppNavHost)
                }.ScreenContent()
            }
            screenBuilder(Screen.Contacts) {
                remember {
                    HomeScreen(this@AppNavHost)
                }.ScreenContent()
            }
        }
    }
}