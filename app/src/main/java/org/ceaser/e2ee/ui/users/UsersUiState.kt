package org.ceaser.e2ee.ui.users

import org.ceaser.e2ee.domain.User

data class UsersUiState(
    val list: List<User> = listOf(),
    val offline: Boolean = false
)