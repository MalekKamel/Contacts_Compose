package com.contacts.app.ui.splash

import app.common.data.Repos
import app.common.presentation.mvvm.vm.AppViewModel

/**
 * Created by Sha on 7/28/20.
 */

class SplashViewModel(private val repos: Repos) : AppViewModel() {

    companion object {
        fun build(): SplashViewModel {
            return SplashViewModel(Repos.build())
        }
    }
}