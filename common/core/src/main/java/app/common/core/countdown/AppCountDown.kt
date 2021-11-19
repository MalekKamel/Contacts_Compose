package app.common.core.countdown

import android.os.CountDownTimer
import app.common.core.date.AppDate
import org.threeten.bp.Duration
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class AppCountDown(private val duration: Duration) {
    var onTick: ((Output) -> Unit)? = null
    var onFinish: (() -> Unit)? = null
    private var timer: Timer? = null
    var isActive = false
    val output = Output()

    data class Output(
        var minuteSecondText: String = "",
        var day: Int = 0,
        var hour: Int = 0,
        var minute: Int = 0,
        var second: Int = 0
    ) {
        fun reset(): Output {
            minuteSecondText = ""
            day = 0
            hour = 0
            minute = 0
            second = 0
            return this
        }
    }


    fun start() {
        setupTimer()
        isActive = true
    }

    private fun setupTimer() {
        val diff: Long = abs(duration.toMillis())

        timer = Timer(diff, TimeUnit.SECONDS.toMillis(1))
        timer?.start()
    }

    fun cancel() {
        timer?.cancel()
        isActive = false
    }

    inner class Timer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
            val date = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(millisUntilFinished),
                ZoneId.systemDefault()
            )
            output.minuteSecondText = AppDate.format(date, AppDate.Format.MM_SS)!!
            output.day = date.dayOfYear
            output.hour = date.hour
            output.minute = date.minute
            output.second = date.second

            onTick?.invoke(output)
        }

        override fun onFinish() {
            this.cancel()
            onTick?.invoke(output.reset())
            onFinish?.invoke()
            isActive = false
        }
    }

    companion object {
        fun create(
            amount: Duration,
            block: AppCountDown.() -> Unit
        ): AppCountDown {
            return AppCountDown(amount).apply(block)
        }
    }
}
