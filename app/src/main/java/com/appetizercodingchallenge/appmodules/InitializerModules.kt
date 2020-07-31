package com.appetizercodingchallenge.appmodules

import com.appetizercodingchallenge.appinitializers.AppInitializer
import com.appetizercodingchallenge.appinitializers.AppInitializers
import com.appetizercodingchallenge.appinitializers.ThreeTenBpInitializer
import com.appetizercodingchallenge.appinitializers.TimberInitializer
import com.appetizercodingchallenge.util.AccLogger
import com.appetizercodingchallenge.util.AppCoroutineDispatchers
import org.koin.dsl.module

val initializerModules = module {
    factory { provideSetOfInitializers(get(), get()) }
    factory { provideInitializers(get()) }
}

fun provideSetOfInitializers(
    logger: AccLogger,
    dispatchers: AppCoroutineDispatchers
): Set<AppInitializer> = hashSetOf(
    TimberInitializer(logger),
    ThreeTenBpInitializer(dispatchers)
)

fun provideInitializers(initializers: Set<AppInitializer>): AppInitializers =
    AppInitializers(initializers)
