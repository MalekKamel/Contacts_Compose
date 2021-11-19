package com.contacts.app.ui.home

import android.Manifest
import android.os.Bundle
import app.common.core.request_result.permission.PermissionRequester
import app.common.presentation.ui.frag.AppFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Sha on 7/28/20.
 */

class HomeFragment : AppFragment<HomeViewModel, HomeScreen.Route>() {
    override val vm: HomeViewModel by viewModel()

    override val screen = HomeScreen(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeContacts()
        observeContactSync()
    }

    override fun onResume() {
        super.onResume()
        loadContacts()
    }

    private fun observeContactSync() {
        vm.onSync.observe(this) { isModified ->
            if (!isModified) return@observe
            loadContacts()
        }
    }

    private fun observeContacts() {
        vm.contacts.observe(this) {
            syncContacts()
        }
    }

    private fun loadContacts() {
        PermissionRequester(requireActivity())
            .request(
                Manifest.permission.READ_CONTACTS
            ) {
                vm.loadContacts()
            }
    }

    private fun syncContacts() {
        vm.syncContacts()
    }

    override fun navigate(to: HomeScreen.Route) {
    }

}