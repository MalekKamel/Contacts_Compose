package com.contacts.app.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import app.common.presentation.compose.theme.AppTheme
import com.contacts.app.shared.navigation.AppNavHost

/**
 * Created by Sha on 7/28/20.
 */

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Content()
                }
            }
        }
    }

    @Composable
    private fun Content() {
        val navController = rememberNavController()
        remember {
            AppNavHost(this, navController)
        }
            .Setup()
    }
}

