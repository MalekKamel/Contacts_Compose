package com.contacts.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.common.data.Repos
import app.common.data.model.ContactItem
import app.common.presentation.mvvm.vm.AppViewModel
import com.sha.coroutinerequester.RequestOptions
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Sha on 7/28/20.
 */

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}

class HomeViewModel(repos: Repos) : AppViewModel(repos) {
    private val _contacts = MutableLiveData<List<ContactItem>>()
    val contacts: LiveData<List<ContactItem>> = _contacts
    val onSync = MutableLiveData<Boolean>()

    fun loadContacts() {
        request {
            val response = dm.contacts.contacts()
            _contacts.postValue(response)
        }
    }

    fun syncContacts() {
        val options = RequestOptions.Builder()
            .showLoading(false)
            .build()
        request(options) {
            val response = dm.contacts.sync()
            onSync.postValue(response)
        }
    }
}