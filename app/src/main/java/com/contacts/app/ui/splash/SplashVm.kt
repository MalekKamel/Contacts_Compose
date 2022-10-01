package com.contacts.app.ui.splash

import app.common.data.Repos
import app.common.presentation.mvvm.vm.AppVm

/**
 * Created by Sha on 7/28/20.
 */

class SplashVm(private val repos: Repos) : AppVm() {

    companion object {
        fun build(): SplashVm {
            return SplashVm(Repos.build())
        }
    }
}