package com.contacts.app.ui.splash

import android.os.Handler
import android.os.Looper
import app.common.presentation.compose.navigator.AppNavigator
import app.common.presentation.ui.fragment.AppFragment
import com.contacts.app.ui.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

/**
 * Created by Sha on 7/28/20.
 */

class SplashFragment :
    AppFragment<SplashViewModel, SplashScreen.Route>() {
    override val vm: SplashViewModel by viewModel()
    override val screen = SplashScreen(this)

    override fun onResume() {
        super.onResume()
        setupFlow()
    }

    private fun setupFlow() {
        Handler(Looper.getMainLooper()).postDelayed({
            showHome()
        }, TimeUnit.SECONDS.toMillis(2))
    }

    private fun showHome() {
        AppNavigator(requireActivity()).replace(HomeFragment(), addToBackStack = false)
    }

    override fun navigate(to: SplashScreen.Route) {
    }

}
