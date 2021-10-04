package com.launcher.app.repo

import com.launcher.app.data.AppInfo
import com.launcher.app.data.AppInfoProvider
import com.launcher.app.network.DenyListService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

/**
 * Domain layer repo used for the view model to receive all the apps it can show
 */
internal interface AppInfoRepository {
    suspend fun getApps(): Result<List<AppInfo>>
}

/**
 * Impl of the repo combines the deny list service and app infor provider
 * to map together a list of all apps with the correct status to show
 */
internal class AppInfoRepositoryImpl @Inject constructor(
    private val denyListService: DenyListService,
    private val appInfoProvider: AppInfoProvider
) : AppInfoRepository {
    private val dispatcher = Dispatchers.Default
    private val scope: CoroutineScope = CoroutineScope(dispatcher)

    override suspend fun getApps(): Result<List<AppInfo>> {
        val deniedList = scope.async {
            denyListService.getDenyList().deniedApps
        }.await()

        val appListResult = appInfoProvider.getInstalledApps()

        if (appListResult.isFailure) {
            return Result.failure(appListResult.exceptionOrNull() ?: UnknownError("Could not find apps"))
        }

        val updatedReturnList = mutableListOf<AppInfo>()
        val appList = appListResult.getOrNull()
        appList?.forEach { appInfo ->
            if (deniedList.contains(appInfo.packageName)) {
                val updated = appInfo.withEnabled(false)
                updatedReturnList.add(updated)
            } else {
                updatedReturnList.add(appInfo)
            }
        }
        return Result.success(updatedReturnList)
    }
}