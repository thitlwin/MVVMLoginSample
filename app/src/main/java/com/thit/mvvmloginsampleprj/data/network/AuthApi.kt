package com.thit.mvvmloginsampleprj.data.network

import com.thit.mvvmloginsampleprj.data.responses.LoginResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthApi {

    @GET("api/login")
    suspend fun login(
        @Query("username")
        email: String,
        @Query("password")
        password: String
    ) : LoginResponse
}