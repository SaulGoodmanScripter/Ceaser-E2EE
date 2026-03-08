package org.ceaser.e2ee.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.ceaser.e2ee.domain.User
import org.ceaser.e2ee.ui.components.ChatScreen
import org.ceaser.e2ee.ui.components.ContactsDrawer
import org.ceaser.e2ee.ui.users.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeApp() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedContact by remember { mutableStateOf<User?>(null) }

    val usersViewModel = hiltViewModel<UsersViewModel>()
    val usersState = usersViewModel.uiState

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ContactsDrawer(
                contacts = usersState.list,
                onContactClick = { user ->
                    selectedContact = user
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Ceaser E2EE Messenger") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                if (selectedContact != null) {
                    ChatScreen(contact = selectedContact!!)
                } else {
                    Text(
                        text = "Select a contact to start chatting",
                        modifier = Modifier.fillMaxSize(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }
    }
}