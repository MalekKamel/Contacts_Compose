package com.contacts.app.shared

import android.Manifest
import app.common.core.app.CoreApp
import app.common.core.request_result.permission.PermissionRequester
import app.common.data.work.AppWorkManager
import com.contacts.app.R
import com.contacts.app.shared.di.KoinInjector
import com.sha.kamel.navigator.NavigatorOptions

/**
 * Created by Sha on 13/04/17.
 */

class App : CoreApp() {

    override fun onCreate() {
        super.onCreate()
        KoinInjector.inject(this)

        startContactsWorker()
        NavigatorOptions.frameLayoutId = R.id.mainFrameLayout
    }

    private fun startContactsWorker() {
        if (!PermissionRequester.isGranted(Manifest.permission.READ_CONTACTS, context)) return
        AppWorkManager.startContactsWorker()

    }
}
