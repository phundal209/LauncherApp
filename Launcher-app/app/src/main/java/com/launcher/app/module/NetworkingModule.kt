package com.launcher.app.module

import com.launcher.app.network.RetrofitServiceProvider
import com.launcher.app.network.RetrofitServiceProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkingModule {
    @Binds
    internal abstract fun bindRetrofitService(impl: RetrofitServiceProviderImpl): RetrofitServiceProvider
}