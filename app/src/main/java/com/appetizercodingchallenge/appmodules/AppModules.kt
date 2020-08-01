package com.appetizercodingchallenge.appmodules

import com.appetizercodingchallenge.BuildConfig
import com.appetizercodingchallenge.util.AccLogger
import com.appetizercodingchallenge.util.AppCoroutineDispatchers
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import java.text.SimpleDateFormat
import java.util.Locale

val appModules = module {
    factory { provideLogger() }
    factory { provideAppCoroutineDispatchers() }
    factory(qualifier("lastUserVisitFormatter")) { provideSimpleDateFormatter() }
}

private fun provideLogger(): AccLogger = AccLogger(BuildConfig.DEBUG)

private fun provideAppCoroutineDispatchers() = AppCoroutineDispatchers()

private fun provideSimpleDateFormatter() = SimpleDateFormat("MMM dd, yyyy hh:mm:ss", Locale.US)

private fun provideSimpleDateFormatterMonthYear() = SimpleDateFormat("MMM yyyy", Locale.US)
