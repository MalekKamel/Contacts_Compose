package app.common.core.alarm_manager

import android.app.AlarmManager
import android.app.PendingIntent

class BackgroundTaskBuilder {
    var alarmIntent: PendingIntent? = null
    var alarmManagerType: Int = AlarmManager.ELAPSED_REALTIME_WAKEUP

    companion object {
        fun create(builder: BackgroundTaskBuilder.() -> Unit)
                : BackgroundTaskBuilder {
            return BackgroundTaskBuilder().apply(builder)
        }
    }

}