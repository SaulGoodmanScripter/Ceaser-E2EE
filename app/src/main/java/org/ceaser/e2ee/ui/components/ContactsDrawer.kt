package org.ceaser.e2ee.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.ceaser.e2ee.domain.User

@Composable
fun ContactsDrawer(
    contacts: List<User>,
    onContactClick: (User) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .padding(16.dp)
    ) {
        Text(
            text = "Contacts",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(contacts) { contact ->
                ContactItem(contact = contact, onClick = { onContactClick(contact) })
            }
        }
    }
}

@Composable
fun ContactItem(contact: User, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = contact.avatarUrl,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = contact.username, style = MaterialTheme.typography.bodyLarge)
    }
}