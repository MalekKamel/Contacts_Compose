package com.contacts.app.ui.main

import android.os.Bundle
import app.common.presentation.compose.navigator.AppNavigator
import app.common.presentation.navigation.NavHostActivity
import com.contacts.app.ui.splash.SplashFragment

/**
 * Created by Sha on 7/28/20.
 */

class MainActivity : NavHostActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppNavigator(this).replace(SplashFragment(), addToBackStack = false)
    }
}
