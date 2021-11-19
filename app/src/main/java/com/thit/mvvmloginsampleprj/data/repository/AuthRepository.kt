package com.thit.mvvmloginsampleprj.data.repository

import android.util.Log
import com.thit.mvvmloginsampleprj.data.UserPreferences
import com.thit.mvvmloginsampleprj.data.network.AuthApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val userPreferences: UserPreferences
) : BaseRepository() {
    suspend fun login(email: String, password: String) = safeApiCall {
        Log.d("---AuthRepository---", "email=$email, password=$password")
        api.login(email, password)
    }

    suspend fun saveAuthToken(token: String) {
        userPreferences.saveAuthToken(token)
    }
}