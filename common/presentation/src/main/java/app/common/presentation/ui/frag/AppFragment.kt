package app.common.presentation.ui.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import app.common.presentation.theme.AppTheme
import app.common.presentation.ui.activity.BaseActivity
import app.common.presentation.ui.view.ViewInterface
import app.common.presentation.ui.vm.AppViewModel

abstract class AppFragment<VM : AppViewModel> : Fragment(), ViewInterface {
    abstract val screen: @Composable () -> Unit
    abstract val vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupVm()
    }

    private fun setupVm() {
        vm.toggleLoading.observe(this, Observer { show ->
            if (show) {
                showLoadingDialog()
                return@Observer
            }
            dismissLoadingDialogs()
        })
        vm.showError.observe(this) { showErrorInFlashBar(it) }
        vm.showErrorRes.observe(this) { showErrorInFlashBar(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    screen()
                }
            }
        }
    }

    override fun activity(): BaseActivity? = activity as? BaseActivity

}