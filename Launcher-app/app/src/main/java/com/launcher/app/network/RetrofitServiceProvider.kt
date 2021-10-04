package com.launcher.app.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * Provides a service class built on retrofit
 */
interface RetrofitServiceProvider {
    fun <T> create(clazz : Class<T>): T
}

/**
 * Implementation of the retrofit service provider
 * Uses moshi/okhttp for serialization and client building of the network layer
 */
internal class RetrofitServiceProviderImpl @Inject constructor(
    private val moshi: Moshi
): RetrofitServiceProvider {
    override fun <T> create(clazz: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io")
            .client(createOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(moshi))

        return retrofit.build().create(clazz) as T
    }

    private fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
