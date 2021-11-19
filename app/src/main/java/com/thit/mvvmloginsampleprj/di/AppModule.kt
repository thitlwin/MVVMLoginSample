package com.thit.mvvmloginsampleprj.di

import android.content.Context
import com.thit.mvvmloginsampleprj.data.network.AuthApi
import com.thit.mvvmloginsampleprj.data.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthApi(
    remoteDataSource: RemoteDataSource) : AuthApi {
        return remoteDataSource.buildApi(AuthApi::class.java)
    }
}