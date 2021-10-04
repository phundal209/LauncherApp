package com.launcher.app.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class MoshiModule {
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()
}