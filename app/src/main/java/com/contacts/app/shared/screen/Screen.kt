package com.contacts.app.shared.screen

import app.common.presentation.compose.screen.ScreenRoute

sealed class Screen(override val route: String) : ScreenRoute {
    object Splash : Screen(route = "splash_screen")
    object Contacts : Screen(route = "contacts_screen")
}