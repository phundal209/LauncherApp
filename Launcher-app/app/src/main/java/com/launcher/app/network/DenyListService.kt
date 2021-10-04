package com.launcher.app.network

import retrofit2.http.GET

/**
 * Service that returns a list of offline networks
 */
internal interface DenyListService {
    @GET("/b/61575fdf4a82881d6c5923a5")
    suspend fun getDenyList(): OfflineAppInfoNetworkModel
}