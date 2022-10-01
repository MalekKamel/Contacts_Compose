package app.common.presentation.progress_dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.TextView
import app.common.presentation.R

object AppProgressDialog {
    fun show(context: Context, message: String? = null): Dialog {
        val dialog = Dialog(context)
        val inflate = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)

        if (message != null) {
            val tv: TextView = inflate.findViewById(R.id.message)
            tv.text = message
        }

        dialog.setContentView(inflate)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        return dialog
    }
}