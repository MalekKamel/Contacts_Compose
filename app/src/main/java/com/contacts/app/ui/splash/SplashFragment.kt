package com.contacts.app.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.navigation.fragment.findNavController
import app.common.presentation.ui.frag.AppFragment
import com.contacts.app.R
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Sha on 7/28/20.
 */

class SplashFragment : AppFragment<SplashViewModel>() {
    override val vm: SplashViewModel by viewModel()

    override val screen: @Composable () -> Unit = {
        SplashScreen()
    }

    override fun onResume() {
        super.onResume()
        setupFlow()
    }

    private fun setupFlow() {
        Handler(Looper.getMainLooper()).postDelayed({
            showHome()
        }, 2000)
    }

    private fun showHome() {
        findNavController().navigate(R.id.action_splashFrag_to_homeFrag)
    }

}
