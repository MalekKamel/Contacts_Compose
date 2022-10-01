package com.contacts.app.ui.home

import androidx.compose.runtime.mutableStateListOf
import app.common.data.Repos
import app.common.data.model.ContactItem
import app.common.presentation.mvvm.vm.AppVm
import com.sha.coroutinerequester.RequestOptions

/**
 * Created by Sha on 7/28/20.
 */

class HomeVm(private val repos: Repos) : AppVm() {
    val contacts = mutableStateListOf<ContactItem>()

    fun loadContacts() {
        request {
            val response = repos.contacts.contacts()
            contacts.clear()
            contacts.addAll(response)
        }
    }

    fun syncContacts() {
        val options = RequestOptions.Builder()
            .showLoading(false)
            .build()
        request(options) {
            repos.contacts.sync()
        }
    }

    companion object {
        fun build(): HomeVm {
            return HomeVm(Repos.build())
        }
    }
}