package com.launcher.app.module

import com.launcher.app.ui.LauncherAppView
import com.launcher.app.ui.LauncherAppViewImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class LauncherAppViewModule {
    @Binds
    internal abstract fun providesLauncherAppView(impl: LauncherAppViewImpl): LauncherAppView
}