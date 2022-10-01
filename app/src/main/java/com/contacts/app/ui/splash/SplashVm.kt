package com.contacts.app.ui.splash

import app.common.data.Repos
import app.common.presentation.mvvm.vm.AppVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Sha on 7/28/20.
 */

val splashModule = module {
    viewModel { SplashVm(get()) }
}

class SplashVm(dataManager: Repos) : AppVm(dataManager)