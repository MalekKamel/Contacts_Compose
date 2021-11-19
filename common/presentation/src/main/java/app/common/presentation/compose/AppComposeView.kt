package app.common.presentation.compose

import app.common.presentation.mvvm.vm.AppViewModel
import app.common.presentation.ui.frag.AppFragment

open class AppComposeView(
    private val vm: AppViewModel,
    private val appFragment: AppFragment<*, *>
) {

    init {
        setupVm()
    }

    private fun setupVm() {
        vm.setupWith(appFragment)
    }
}