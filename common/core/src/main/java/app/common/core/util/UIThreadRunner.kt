package app.common.core.util

import android.os.Handler
import android.os.Looper

object UIThreadRunner {

    fun run(runnable: () -> Unit) {
        Handler(Looper.getMainLooper()).post(runnable)
    }
}
