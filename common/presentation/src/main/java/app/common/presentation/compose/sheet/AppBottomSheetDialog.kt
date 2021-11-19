package app.common.presentation.compose.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.FragmentManager
import app.common.presentation.compose.theme.AppTheme
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class AppBottomSheetDialog : BottomSheetDialogFragment() {

    @Composable
    abstract fun Content()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    this@AppBottomSheetDialog.Content()
                }
            }
        }
    }

    fun show(manager: FragmentManager) {
        super.show(manager, AppBottomSheetDialog::class.java.name)
    }
}

