package com.contacts.app.ui.home

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.common.core.request_result.permission.PermissionRequester
import app.common.data.model.ContactItem
import app.common.presentation.compose.screen.NavControllerHost
import app.common.presentation.mvvm.AppScreen


class HomeScreen(
    override val host: NavControllerHost,
) : AppScreen<HomeVm>() {
    override val vm = HomeVm.build()

    @Composable
    override fun Content() {
        LaunchedEffect(true) {
            loadContacts()
        }
        ContactsList()
    }

    @Composable
    fun ContactsList() {
        LazyColumn {
            items(vm.contacts) {
                ContactCell(item = it)
            }
        }
    }

    @Composable
    fun ContactCell(item: ContactItem) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = item.name ?: "",
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = item.phone ?: "",
                color = Color.Blue,
            )
        }
    }

    private fun loadContacts() {
        PermissionRequester(activity())
            .request(
                Manifest.permission.READ_CONTACTS
            ) {
                vm.loadContacts()
            }
    }

}