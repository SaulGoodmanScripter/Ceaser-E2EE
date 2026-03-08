package org.ceaser.e2ee.network

import org.ceaser.e2ee.network.model.UserApiModel
import retrofit2.http.GET

interface UsersApi {

    @GET("/repos/square/retrofit/stargazers")
    suspend fun getUsers(): List<UserApiModel>
}