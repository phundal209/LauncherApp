package com.launcher.app.module

import com.launcher.app.data.AppInfoProvider
import com.launcher.app.data.AppInfoProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AppInfoModule {
    @Binds
    internal abstract fun bindAppInfoProvider(impl: AppInfoProviderImpl): AppInfoProvider
}