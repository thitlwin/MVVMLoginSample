package com.thit.mvvmloginsampleprj.data.network

import retrofit2.http.GET

interface CustomerApi {
    @GET("customers")
    suspend fun getCustomer()
}