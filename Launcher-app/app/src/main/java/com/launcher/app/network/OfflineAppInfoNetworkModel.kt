package com.launcher.app.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Network model for list of apps that are denied
 */
@JsonClass(generateAdapter = true)
internal data class OfflineAppInfoNetworkModel(
    @Json(name = "denylist")
    val deniedApps: List<String>
)
