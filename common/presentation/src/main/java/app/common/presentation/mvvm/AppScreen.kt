package app.common.presentation.mvvm

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.fragment.app.FragmentActivity
import app.common.core.runOnMainThread
import app.common.presentation.R
import app.common.presentation.compose.ComposeCircularProgress
import app.common.presentation.compose.screen.NavControllerHost
import app.common.presentation.compose.screen.ScreenRoute
import app.common.presentation.flashbar.AppFlashBar
import app.common.presentation.mvvm.vm.AppVm
import java.util.concurrent.TimeUnit

abstract class AppScreen<VM : AppVm> {
    abstract val vm: VM
    abstract val host: NavControllerHost

    @Composable
    protected abstract fun Content()

    @Composable
    fun ScreenContent() {
        LaunchedEffect(true) {
            setupVm()
        }
        Content()
        ShowLoaderProgress()
    }

    private fun setupVm() {
        vm.setupWith(this)
    }

    fun activity() = host.activity
    fun context(): Context = host.activity.baseContext

    fun navigate(route: ScreenRoute) = host.navigate(route)
    fun navigate(route: String) = host.navigate(route)

    @Composable
    private fun ShowLoaderProgress() {
        if (vm.toggleLoading.value) {
            ComposeCircularProgress().Content()
        }
    }

    fun showLoading(message: String) {

    }

    fun showError(message: String) {
        Reporter.showError(message, activity())
    }

    fun showError(message: Int) {
        Reporter.showError(message, activity())
    }

    fun showSuccess(message: String) {
        Reporter.showSuccess(message, activity())
    }
}

object Reporter {

    fun showError(message: String, activity: FragmentActivity) {
        runOnMainThread {
            AppFlashBar.show(
                activity,
                message,
                null,
                TimeUnit.SECONDS.toMillis(2),
                R.color.red
            )
        }
    }

    fun showError(message: Int, activity: FragmentActivity) {
        runOnMainThread {
            AppFlashBar.show(
                activity,
                message,
                null,
                TimeUnit.SECONDS.toMillis(2),
                R.color.red
            )
        }
    }

    fun showSuccess(message: String, activity: FragmentActivity) {
        runOnMainThread {
            AppFlashBar.show(
                activity,
                message,
                null,
                TimeUnit.SECONDS.toMillis(2),
                R.color.green
            )
        }
    }
}