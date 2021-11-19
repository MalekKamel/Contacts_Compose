package app.common.core.request_result.start_activity

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.fragment.app.FragmentActivity

class ActivityResultRequester(private val activity: FragmentActivity) {

    fun request(intent: Intent, callback: (ActivityResult) -> Unit) {
        when {
            else -> ActivityResultFragment.request(intent, activity, callback)
        }
    }
}