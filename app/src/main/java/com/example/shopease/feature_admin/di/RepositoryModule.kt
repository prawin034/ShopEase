package com.example.shopease.feature_admin.di

import android.content.Context
import com.example.shopease.feature_admin.data.remote.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(@ApplicationContext context : Context) : ApiRepository{
        return  ApiRepository(context)
    }



}