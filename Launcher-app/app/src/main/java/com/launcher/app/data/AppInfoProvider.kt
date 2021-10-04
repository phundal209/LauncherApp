package com.launcher.app.data

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import javax.inject.Inject

/**
 * Provides installed apps data
 */
internal interface AppInfoProvider {
    fun getInstalledApps(): Result<List<AppInfo>>
}

internal class AppInfoProviderImpl @Inject constructor(
    private val pm: PackageManager
) : AppInfoProvider {
    override fun getInstalledApps(): Result<List<AppInfo>> {
        val main = Intent(Intent.ACTION_MAIN, null)
        main.addCategory(Intent.CATEGORY_LAUNCHER)
        val packages: List<ResolveInfo> = pm.queryIntentActivities(main, 0)
        return pm.runCatching {
            packages.map {
                val packageName = it.activityInfo.packageName
                val appName =
                    pm.getApplicationLabel(pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA)).toString()
                val packageIcon = pm.getApplicationIcon(packageName)
                AppInfo(
                    packageIcon = packageIcon,
                    packageLabel = appName,
                    packageName = packageName
                )
            }.toList()
        }
    }
}