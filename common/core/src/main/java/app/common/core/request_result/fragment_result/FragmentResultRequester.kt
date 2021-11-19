package app.common.core.request_result.fragment_result

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class FragmentResultRequester(private val activity: FragmentActivity) {

    fun request(requestKey: FragmentResultKeyType, callback: (Bundle) -> Unit) {
        when {
            else -> ResultListenerFragment.request(requestKey.value, activity, callback)
        }
    }
}