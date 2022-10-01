package app.common.presentation.mvvm.vm

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.common.presentation.mvvm.AppScreen
import app.common.presentation.requester.AppRequester
import com.sha.coroutinerequester.Presentable
import com.sha.coroutinerequester.RequestOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class AppVm : ViewModel() {
    var toggleLoading = mutableStateOf(false)
    val showError = mutableStateOf("")
    val showErrorRes = mutableStateOf(0)

    private val requester: AppRequester by lazy {
        val presentable = object : Presentable {
            override fun showError(error: String) {
                Handler(Looper.getMainLooper()).post {
                    showError.value = error
                }
            }

            override fun showError(error: Int) {
                Handler(Looper.getMainLooper()).post {
                    showErrorRes.value = error
                }
            }

            override fun showLoading() {
                Handler(Looper.getMainLooper()).post {
                    toggleLoading.value = true
                }
            }

            override fun hideLoading() {
                Handler(Looper.getMainLooper()).post {
                    toggleLoading.value = false
                }
            }

            override fun onHandleErrorFailed(throwable: Throwable) {
                showError.value = throwable.message ?: ""
            }
        }
        AppRequester(presentable)
    }

    /**
     * Each request should call this function to apply Coroutines and
     * handle exceptions
     */
    fun request(
        options: RequestOptions = RequestOptions.default(),
        context: CoroutineContext = Dispatchers.IO,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(context) {
            requester.request(options, block)
        }
    }

    fun setupWith(screen: AppScreen<*>) {
        snapshotFlow { showError.value }
            .drop(1)
            .onEach { e ->
                screen.showError(e)
            }
            .launchIn(viewModelScope)
        snapshotFlow { showErrorRes.value }
            .drop(1)
            .onEach { e ->
                screen.showError(e)
            }
            .launchIn(viewModelScope)
    }

}

