package com.contacts.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.common.data.DataManager
import app.common.data.model.ContactItem
import app.common.presentation.ui.vm.AppViewModel
import com.sha.coroutinerequester.RequestOptions
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Sha on 7/28/20.
 */

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}

class HomeViewModel(dataManager: DataManager) : AppViewModel(dataManager) {
    private val _contacts = MutableLiveData<List<ContactItem>>()
    val contacts: LiveData<List<ContactItem>> = _contacts
    val onSync = MutableLiveData<Boolean>()

    fun loadContacts() {
        request {
            val response = dm.contactsRepo.contacts()
            _contacts.postValue(response)
        }
    }

    fun syncContacts() {
        val options = RequestOptions.Builder()
            .showLoading(false)
            .build()
        request(options) {
            val response = dm.contactsRepo.sync()
            onSync.postValue(response)
        }
    }
}