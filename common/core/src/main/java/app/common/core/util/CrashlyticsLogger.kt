package app.common.core.util

/**
 * Created by Sha on 10/15/17.
 */

// TODO: integrate with Crashlytics
object CrashlyticsLogger {

    fun logAndPrint(throwable: Throwable) {
        // In production we must uncomment
        throwable.printStackTrace()
//        Crashlytics.getInstance().core.logException(throwable)
    }

    fun logAndPrint(msg: String) {
        // In production we must uncomment
//        Crashlytics.getInstance().core.logException(Throwable(msg))
    }
}

fun Throwable.reportAndPrint() {
    CrashlyticsLogger.logAndPrint(this)
}