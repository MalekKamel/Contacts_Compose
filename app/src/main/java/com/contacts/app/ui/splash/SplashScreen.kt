package com.contacts.app.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import app.common.presentation.compose.screen.NavControllerHost
import app.common.presentation.mvvm.AppScreen
import com.contacts.app.shared.screen.Screen

class SplashScreen(
    override val host: NavControllerHost,
) : AppScreen<SplashVm>() {
    override val vm = SplashVm.build()

    @Composable
    override fun Content() {
        LaunchedEffect(true) {
            Handler(Looper.getMainLooper()).postDelayed({
                navigate(Screen.Contacts)
            }, 1000)
        }

        Surface(modifier = Modifier.fillMaxSize()) {
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
                    color = Color.Black,
                    fontSize = 50.sp
                )
            }
        }
    }
}