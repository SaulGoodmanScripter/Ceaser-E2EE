package org.ceaser.e2ee.repository

import org.ceaser.e2ee.database.AppDatabase
import org.ceaser.e2ee.database.asDomainModel
import org.ceaser.e2ee.domain.User
import org.ceaser.e2ee.network.UsersApi
import org.ceaser.e2ee.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersApi: UsersApi,
    private val appDatabase: AppDatabase
) {

    val users: Flow<List<User>?> =
        appDatabase.usersDao.getUsers().map { it?.asDomainModel() }

    suspend fun refreshUsers() {
        try {
            val users = usersApi.getUsers()
            appDatabase.usersDao.insertUsers(users.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}