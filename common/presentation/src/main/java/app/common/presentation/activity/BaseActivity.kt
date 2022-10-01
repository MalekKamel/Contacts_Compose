package app.common.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.common.core.util.reportAndPrint

abstract class BaseActivity
    : AppCompatActivity() {

    abstract var layoutId: Int

    protected open fun bindUi() {}
    protected open fun doOnCreate() {}
    protected open fun doOnCreate(savedInstanceState: Bundle?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            if (layoutId != 0)
                setContentView(layoutId)

            bindUi()
            doOnCreate()
            doOnCreate(savedInstanceState)

        } catch (e: Exception) {
            e.reportAndPrint()
        }
    }
}
