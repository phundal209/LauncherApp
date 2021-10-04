package com.launcher.app.module

import com.launcher.app.repo.AppInfoRepository
import com.launcher.app.repo.AppInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AppInfoRepositoryModule {
    @Binds
    internal abstract fun bindAppInfoRepository(impl: AppInfoRepositoryImpl): AppInfoRepository
}