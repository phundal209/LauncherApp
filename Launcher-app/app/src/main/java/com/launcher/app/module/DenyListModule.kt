package com.launcher.app.module

import com.launcher.app.network.DenyListService
import com.launcher.app.network.RetrofitServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class DenyListModule {
    @Provides
    fun providesDenyListService(retrofitServiceProvider: RetrofitServiceProvider): DenyListService {
        return retrofitServiceProvider.create(DenyListService::class.java)
    }
}