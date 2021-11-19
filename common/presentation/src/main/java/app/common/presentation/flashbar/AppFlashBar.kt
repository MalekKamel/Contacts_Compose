package app.common.presentation.flashbar

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import app.common.presentation.R
import com.andrognito.flashbar.Flashbar
import com.sha.bulletin.flashbar.StandardFlashbar
import com.sha.bulletin.showBulletin
import java.util.concurrent.TimeUnit

object AppFlashBar {

    fun show(
        activity: FragmentActivity?,
        content: String,
        @DrawableRes icon: Int? = null,
        duration: Long = TimeUnit.SECONDS.toMillis(2),
        @ColorRes backgroundColor: Int = R.color.quantum_googgreen
    ) {
        if (activity == null) return
        val builder = Flashbar.Builder(activity)
            .message(content)
            .gravity(Flashbar.Gravity.TOP)
            .duration(duration)
            .enableSwipeToDismiss()
            .backgroundColorRes(backgroundColor)
        if (icon != null) builder.icon(icon)
        show(activity, builder)
    }

    fun show(
        activity: FragmentActivity?,
        @StringRes contentRes: Int,
        @DrawableRes icon: Int? = null,
        duration: Long = TimeUnit.SECONDS.toMillis(2),
        @ColorRes backgroundColor: Int = R.color.quantum_googgreen
    ) {
        if (activity == null) return
        val builder = Flashbar.Builder(activity)
            .message(contentRes)
            .gravity(Flashbar.Gravity.TOP)
            .duration(duration)
            .enableSwipeToDismiss()
            .backgroundColorRes(backgroundColor)
        if (icon != null) builder.icon(icon)
        show(activity, builder)
    }

    fun show(
        activity: FragmentActivity?,
        builder: Flashbar.Builder,
        options: StandardFlashbar.Options = StandardFlashbar.Options.default()
    ) {
        if (activity == null) return
        val bulletin = StandardFlashbar.create(builder, options)
        showBulletin(bulletin, activity)
    }
}