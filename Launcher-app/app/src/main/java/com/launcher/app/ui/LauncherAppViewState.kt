package com.launcher.app.ui

import com.launcher.app.data.AppInfo

sealed class LauncherAppViewState {
    object Initial : LauncherAppViewState()
    object Loading : LauncherAppViewState()
    data class Success(val apps: List<AppInfo>) : LauncherAppViewState()
    data class Error(val error: String) : LauncherAppViewState()
}
