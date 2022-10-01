package app.common.presentation.mvvm.vm

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.common.data.Repos
import app.common.presentation.requester.AppRequester
import app.common.presentation.fragment.AppFragment
import com.sha.coroutinerequester.Presentable
import com.sha.coroutinerequester.RequestOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class AppVm(val dm: Repos) : ViewModel() {
    val toggleLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()
    val showErrorRes = MutableLiveData<Int>()

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
                showError.postValue(throwable.message)
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

    fun setupWith(appFragment: AppFragment<*>?) {
        if (appFragment == null) return
        toggleLoading.observe(appFragment, Observer { show ->
            if (show) {
                appFragment.showLoading()
                return@Observer
            }
            appFragment.activity?.runOnUiThread {
                appFragment.dismissLoading()
            }
        })
        showError.observe(appFragment) {
            appFragment.activity?.runOnUiThread {
                appFragment.showErrorInFlashBar(it)
            }
        }
        showErrorRes.observe(appFragment) { appFragment.showErrorInFlashBar(it) }
    }

}

