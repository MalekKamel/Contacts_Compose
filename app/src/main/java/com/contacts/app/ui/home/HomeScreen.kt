package com.contacts.app.ui.home

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
import app.common.data.model.ContactItem
import app.common.presentation.extension.fillMax
import app.common.presentation.mvvm.AppScreen
import app.common.presentation.mvvm.ScreenHost


class HomeScreen(
    override val host: ScreenHost<HomeViewModel, Route>,
) : AppScreen<HomeViewModel, HomeScreen.Route> {
    sealed class Route

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