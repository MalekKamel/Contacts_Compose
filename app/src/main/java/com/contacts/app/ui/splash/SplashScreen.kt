package com.contacts.app.ui.splash

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import app.common.presentation.extension.supportWideScreen
import app.common.presentation.mvvm.AppScreen
import app.common.presentation.mvvm.ScreenHost

class SplashScreen(
    override val host: ScreenHost<SplashViewModel, Route>,
) : AppScreen<SplashViewModel, SplashScreen.Route> {
    sealed class Route

    @Composable
    override fun Content() {
        Surface(modifier = Modifier.supportWideScreen()) {
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
}