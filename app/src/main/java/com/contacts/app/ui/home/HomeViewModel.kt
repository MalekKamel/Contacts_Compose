package com.contacts.app.ui.home

import androidx.compose.runtime.mutableStateListOf
import app.common.data.Repos
import app.common.data.model.ContactItem
import app.common.presentation.mvvm.vm.AppViewModel
import com.sha.coroutinerequester.RequestOptions

/**
 * Created by Sha on 7/28/20.
 */

class HomeViewModel(private val repos: Repos) : AppViewModel() {
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
        fun build(): HomeViewModel {
            return HomeViewModel(Repos.build())
        }
    }
}