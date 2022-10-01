package app.common.presentation.fragment

import android.app.Dialog
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
import app.common.presentation.mvvm.vm.AppVm
import app.common.presentation.progress_dialog.AppProgressDialog


abstract class AppFragment<VM : AppVm> : Fragment() {
    abstract val vm: VM
    private var progressDialog: Dialog? = null

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

    fun showErrorInFlashBar(contentRes: Int) {
        val activity = activity ?: return
        runOnMainThread {
            AppFlashBar.show(
                activity,
                contentRes,
                R.color.red_light
            )
        }
    }

    fun showErrorInFlashBar(content: String) {
        runOnMainThread {
            AppFlashBar.show(
                activity,
                content,
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

    fun showLoading() {
        val context = context ?: return
        progressDialog = AppProgressDialog.show(context)
    }

    fun dismissLoading() {
        progressDialog?.dismiss()
    }


}