package com.appetizercodingchallenge

import android.app.Application
import com.appetizercodingchallenge.appinitializers.AppInitializers
import com.appetizercodingchallenge.data.datasources.dataSourcesModules
import com.appetizercodingchallenge.data.dbModules
import com.appetizercodingchallenge.data.mappers.mapperModules
import com.appetizercodingchallenge.appmodules.appModules
import com.appetizercodingchallenge.data.repositories.repositoryModules
import com.appetizercodingchallenge.domain.interactorModules
import com.appetizercodingchallenge.appmodules.networkModules
import com.appetizercodingchallenge.appmodules.initializerModules
import com.appetizercodingchallenge.appmodules.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class AppController : Application() {
    private val initializers: AppInitializers by inject()
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // inject Android context
            androidContext(this@AppController)
            // use Android logger - Level.INFO by default
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            // use modules
            modules(
                listOf(
                    appModules,
                    initializerModules,
                    networkModules,
                    mapperModules,
                    dataSourcesModules,
                    dbModules,
                    repositoryModules,
                    interactorModules,
                    viewModelModule
                )
            )
        }
        initializers.init(this)
    }
}
