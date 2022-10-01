package com.contacts.app.ui.home

import android.Manifest
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.common.core.request_result.permission.PermissionRequester
import app.common.data.model.ContactItem
import app.common.presentation.extension.fillMax
import app.common.presentation.fragment.AppFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Sha on 7/28/20.
 */

class HomeFragment : AppFragment<HomeVm>() {
    override val vm: HomeVm by viewModel()

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

    @Composable
    override fun Content() {
        Surface(modifier = Modifier.fillMax()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val contacts by vm.contacts.observeAsState()
                ContactsList(contacts)
            }
        }
    }

    @Composable
    fun ContactsList(contacts: List<ContactItem>?) {
        LazyColumn {
            contacts?.forEach {
                item(key = it.id) {
                    ContactCell(item = it)
                }
            }
        }
    }

    @Composable
    fun ContactCell(item: ContactItem) {
        val name = item.name ?: return
        val phone = item.phone ?: return
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = name,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = phone,
                color = Color.Blue,
            )
        }
    }

}