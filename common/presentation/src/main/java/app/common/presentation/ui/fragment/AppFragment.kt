package app.common.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import app.common.core.runOnMainThread
import app.common.presentation.R
import app.common.presentation.compose.theme.AppTheme
import app.common.presentation.flashbar.AppFlashBar
import app.common.presentation.mvvm.vm.AppViewModel
import app.common.presentation.ui.view.ViewInterface

abstract class AppFragment<VM : AppViewModel> : Fragment(),
    ViewInterface {

    abstract val vm: VM

    @Composable
    abstract fun Content()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupVm()
    }

    private fun setupVm() {
        vm.setupWith(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    Content()
                }
            }
        }
    }

    fun popBackStack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    fun showErrorInFlashBar(
        contentRes: Int,
        @DrawableRes icon: Int?,
        duration: Long
    ) {
        val activity = activity ?: return
        runOnMainThread {
            AppFlashBar.show(
                activity,
                contentRes,
                icon,
                duration,
                R.color.red_light
            )
        }
    }

    fun showErrorInFlashBar(
        content: String,
        @DrawableRes icon: Int?,
        duration: Long,
    ) {
        runOnMainThread {
            AppFlashBar.show(
                activity,
                content,
                icon,
                duration,
                R.color.red_light
            )
        }
    }

    fun showFlashBar(
        content: String,
        @DrawableRes icon: Int?,
        duration: Long,
    ) {
        AppFlashBar.show(
            activity,
            content,
            icon,
            duration
        )
    }

}