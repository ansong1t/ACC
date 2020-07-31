package com.appetizercodingchallenge.data.datasources

import org.koin.dsl.module

val dataSourcesModules = module {
    factory { SearchItemDataSource(get(), get()) }
    factory { GetItemDataSource(get(), get()) }
}
