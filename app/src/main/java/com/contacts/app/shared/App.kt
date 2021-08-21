package com.contacts.app.shared

import android.Manifest
import app.common.core.app.CoreApp
import app.common.core.util.reportAndPrint
import app.common.data.work.AppWorkManager
import app.common.presentation.permission.PermissionRequester
import com.contacts.app.shared.di.KoinInjector

/**
 * Created by Sha on 13/04/17.
 */

class App : CoreApp() {

    override fun onCreate() {
        super.onCreate()
        KoinInjector.inject(this)

        startContactsWorker()
    }

    private fun startContactsWorker() {
        if (!PermissionRequester.isGranted(Manifest.permission.READ_CONTACTS, context)) return
        AppWorkManager.startContactsWorker()

    }
}
