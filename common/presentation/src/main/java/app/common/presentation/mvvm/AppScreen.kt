package app.common.presentation.mvvm

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import app.common.core.request_result.fragment_result.FragmentResultKeyType
import app.common.core.request_result.fragment_result.FragmentResultParamBuilder
import app.common.presentation.R
import app.common.presentation.mvvm.vm.AppViewModel
import com.sha.bulletin.BulletinConfig
import java.util.concurrent.TimeUnit

interface AppScreen<VM : AppViewModel, ROUTE> {
    val host: ScreenHost<VM, ROUTE>
    val vm: VM
        get() = host.vm

    fun navigate(to: ROUTE) = host.navigate(to)
    fun popBackStack() {
        vm.toggleLoading.postValue(false)
        host.popBackStack()
    }

    fun activity() = host.activity()
    fun fragment() = host.fragment()
    fun finish() = host.finish()
    fun setFragmentResult(
        key: FragmentResultKeyType,
        params: FragmentResultParamBuilder.() -> Unit
    ) {
        host.setFragmentResult(key, params)
    }

    fun showErrorInFlashBar(
        @StringRes contentRes: Int,
        @DrawableRes icon: Int? = null,
        duration: Long = BulletinConfig.flashBarDuration,
    ) {
        host.showErrorInFlashBar(
            contentRes = contentRes,
            icon = icon,
            duration = duration
        )
    }

    fun showFlashBar(
        content: String,
        @DrawableRes icon: Int? = null,
        duration: Long = TimeUnit.SECONDS.toMillis(2),
        @ColorRes backgroundColor: Int = R.color.quantum_googgreen
    ) {
        host.showFlashBar(
            content = content,
            icon = icon,
            duration = duration,
            backgroundColor = backgroundColor
        )
    }

    @Composable
    fun Content()

    fun onFragmentResume() {}
    fun onFragmentDestroy() {}
}