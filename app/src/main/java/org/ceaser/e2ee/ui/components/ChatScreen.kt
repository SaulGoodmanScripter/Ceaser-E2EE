package org.ceaser.e2ee.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import org.ceaser.e2ee.domain.User
import org.ceaser.e2ee.ui.details.DetailsViewModel

@Composable
fun ChatScreen(contact: User) {
    var messageText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<String>() } // Mock messages

    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val detailsState = detailsViewModel.uiState

    // Load contact details using API stub
    LaunchedEffect(contact.username) {
        detailsViewModel.getDetails(contact.username)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Chat header with contact details
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = contact.avatarUrl,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = contact.username, style = MaterialTheme.typography.headlineSmall)
                detailsState.details?.let {
                    Text(text = it.name ?: "", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        // Messages list
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(messages) { message ->
                MessageBubble(message = message, isMine = messages.indexOf(message) % 2 == 0)
            }
        }

        // Message input
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message...") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (messageText.isNotBlank()) {
                    messages.add(messageText)
                    messageText = ""
                    // Here you could call API to send message, but using stub for now
                }
            }) {
                Text("Send")
            }
        }
    }
}

@Composable
fun MessageBubble(message: String, isMine: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        contentAlignment = if (isMine) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = if (isMine) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            modifier = Modifier.widthIn(max = 250.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(12.dp),
                color = if (isMine) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}