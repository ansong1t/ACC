package com.appetizercodingchallenge.data.datasources

import org.koin.dsl.module

val dataSourcesModules = module {
    factory { GetItemDataSource(get()) }
}
