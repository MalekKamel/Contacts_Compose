package com.contacts.app.ui.main

import android.os.Bundle
import app.common.presentation.R
import app.common.presentation.compose.navigator.AppNavigator
import app.common.presentation.activity.BaseActivity
import com.contacts.app.ui.splash.SplashFragment

/**
 * Created by Sha on 7/28/20.
 */

class MainActivity : BaseActivity() {
    override var layoutId: Int = R.layout.activity_nav_host

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppNavigator(this).replace(SplashFragment(), addToBackStack = false)
    }
}
