package app.common.presentation.navigation

import app.common.presentation.R
import app.common.presentation.ui.activity.BaseActivity

abstract class NavHostActivity : BaseActivity() {
    override var layoutId: Int = R.layout.activity_nav_host
}