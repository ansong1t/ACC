package com.appetizercodingchallenge.appmodules

import android.app.Application
import android.content.Context
import com.appetizercodingchallenge.data.services.ItemService
import com.appetizercodingchallenge.BuildConfig
import com.appetizercodingchallenge.CACHE_SIZE
import com.appetizercodingchallenge.OKHTTP_CONNECT_TIMEOUT_SECONDS
import com.appetizercodingchallenge.OKHTTP_READ_TIMEOUT_SECONDS
import com.appetizercodingchallenge.OKHTTP_WRITE_TIMEOUT_SECONDS
import com.appetizercodingchallenge.network.ServiceInterceptor
import com.appetizercodingchallenge.util.getPref
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val networkModules = module {
    single { provideCache(get()) }
    single { provideServiceInterceptor(get()) }
    single { provideOkHttpClient(get(), get()) }
    factory { provideRetrofit(get()) }
    single { provideSearchService(get()) }
}

private fun provideServiceInterceptor(context: Context) = ServiceInterceptor(context.getPref())

private fun provideCache(application: Application) =
    Cache(File(application.cacheDir, "http-cache"), CACHE_SIZE)

private fun provideOkHttpClient(
    cache: Cache,
    context: Context
) =
    OkHttpClient.Builder().apply {
        cache(cache)
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addInterceptor(ChuckerInterceptor(context))
        }
        addInterceptor(ServiceInterceptor(context.getPref()))
        connectTimeout(OKHTTP_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        readTimeout(OKHTTP_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        writeTimeout(OKHTTP_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
    }.build()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideSearchService(retrofit: Retrofit) =
    retrofit.create(ItemService::class.java)
