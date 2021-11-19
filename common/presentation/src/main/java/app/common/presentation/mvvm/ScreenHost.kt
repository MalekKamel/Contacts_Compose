package app.common.presentation.mvvm

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import app.common.core.request_result.fragment_result.FragmentResultKeyType
import app.common.core.request_result.fragment_result.FragmentResultParamBuilder
import app.common.presentation.R
import app.common.presentation.mvvm.vm.AppViewModel
import app.common.presentation.ui.frag.AppFragment
import com.sha.bulletin.BulletinConfig
import com.sha.bulletin.flashbar.StandardFlashbar
import java.util.concurrent.TimeUnit

interface ScreenHost<VM : AppViewModel, ROUTE> {
    val vm: VM
    fun navigate(to: ROUTE)
    fun popBackStack()
    fun activity(): FragmentActivity?
    fun fragment(): AppFragment<*, *>
    fun getContext(): Context?
    fun finish() {
        activity()?.finish()
    }

    fun showErrorInFlashBar(
        @StringRes contentRes: Int,
        @DrawableRes icon: Int? = null,
        duration: Long = BulletinConfig.flashBarDuration
    )

    fun showFlashBar(
        content: String,
        @DrawableRes icon: Int? = null,
        duration: Long = TimeUnit.SECONDS.toMillis(2),
        @ColorRes backgroundColor: Int = R.color.quantum_googgreen
    )

    fun setFragmentResult(
        key: FragmentResultKeyType,
        params: FragmentResultParamBuilder.() -> Unit
    ) {
        val bundle = FragmentResultParamBuilder().apply(params).bundle
        activity()
            ?.supportFragmentManager
            ?.setFragmentResult(key.value, bundle)
    }
}

