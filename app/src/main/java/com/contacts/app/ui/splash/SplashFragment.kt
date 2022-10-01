package com.contacts.app.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import app.common.presentation.compose.navigator.AppNavigator
import app.common.presentation.extension.fillMax
import app.common.presentation.fragment.AppFragment
import com.contacts.app.ui.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

/**
 * Created by Sha on 7/28/20.
 */

class SplashFragment :
    AppFragment<SplashVm>() {
    override val vm: SplashVm by viewModel()

    @Composable
    override fun Screen() {
        Surface(modifier = Modifier.fillMax()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Contacts",
                    modifier = Modifier,
                    color = Color.Black,
                    fontSize = 50.sp
                )
            }
        }
    }

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

}
