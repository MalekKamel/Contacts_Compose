package app.common.core.alarm_manager

import android.app.AlarmManager
import android.content.Context
import app.common.core.app.CoreApp

//TODO: support android 12 (add Permission)
class AppAlarmManager(private val builder: BackgroundTaskBuilder) {

    private val alarmManager: AlarmManager by lazy {
        CoreApp.context.getSystemService(Context.ALARM_SERVICE)
                as AlarmManager
    }

    companion object {
        fun build(build: BackgroundTaskBuilder.() -> Unit): AppAlarmManager {
            val builder = BackgroundTaskBuilder()
            builder.apply(build)
            return AppAlarmManager(builder)
        }
    }

    fun start(triggerTimer: Long): AppAlarmManager {
        if (builder.alarmIntent == null) return this

        val info = AlarmManager.AlarmClockInfo(
            triggerTimer,
            builder.alarmIntent
        )
        alarmManager.setAlarmClock(info, builder.alarmIntent)

        return this
    }

    fun cancel() {
        if (builder.alarmIntent == null) return
        alarmManager.cancel(builder.alarmIntent)
    }
}