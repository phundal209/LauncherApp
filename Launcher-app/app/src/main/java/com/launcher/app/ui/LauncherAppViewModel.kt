package com.launcher.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.launcher.app.repo.AppInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LauncherAppViewModel @Inject constructor(
    private val appInfoRepository: AppInfoRepository
): ViewModel() {
    private val _viewStates: MutableStateFlow<LauncherAppViewState> = MutableStateFlow(LauncherAppViewState.Initial)
    val viewStates: Flow<LauncherAppViewState>
        get() = _viewStates

    fun getApps() {
        viewModelScope.launch {
            _viewStates.emit(LauncherAppViewState.Loading)
            val result = appInfoRepository.getApps()

            result.onSuccess {
                _viewStates.emit(LauncherAppViewState.Success(it))
            }

            result.onFailure {
                _viewStates.emit(LauncherAppViewState.Error(it.localizedMessage ?: "Error while loading apps"))
            }
        }
    }
}