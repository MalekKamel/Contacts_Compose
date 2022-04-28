package app.common.presentation.ui.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import app.common.core.runOnMainThread
import app.common.presentation.R
import app.common.presentation.compose.theme.AppTheme
import app.common.presentation.flashbar.AppFlashBar
import app.common.presentation.mvvm.AppScreen
import app.common.presentation.mvvm.ScreenHost
import app.common.presentation.mvvm.vm.AppViewModel
import app.common.presentation.ui.view.ViewInterface

abstract class AppFragment<VM : AppViewModel, ROUTE> : Fragment(),
    ScreenHost<VM, ROUTE>,
    ViewInterface {

    abstract val screen: AppScreen<VM, ROUTE>
    abstract override val vm: VM

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
                    screen.Content()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        screen.onFragmentResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        screen.onFragmentDestroy()
    }

    override fun activity(): FragmentActivity? = activity

    override fun fragment(): AppFragment<*, *> {
        return this
    }

    override fun popBackStack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showErrorInFlashBar(
        contentRes: Int,
        @DrawableRes icon: Int?,
        duration: Long
    ) {
        runOnMainThread {
            AppFlashBar.show(
                activity(),
                contentRes,
                icon,
                duration,
                R.color.error
            )
        }
    }

    override fun showErrorInFlashBar(
        content: String,
        @DrawableRes icon: Int?,
        duration: Long,
    ) {
        runOnMainThread {
            AppFlashBar.show(
                activity(),
                content,
                icon,
                duration,
                R.color.error
            )
        }
    }

    override fun showFlashBar(
        content: String,
        @DrawableRes icon: Int?,
        duration: Long,
        backgroundColor: Int,
    ) {
        AppFlashBar.show(
            activity(),
            content,
            icon,
            duration
        )
    }

}