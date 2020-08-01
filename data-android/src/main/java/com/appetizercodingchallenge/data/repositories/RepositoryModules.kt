package com.appetizercodingchallenge.data.repositories

import org.koin.dsl.module

val repositoryModules = module {
    single<ItemRepository> {
        ItemRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    single<TvShowRepository> { TvShowRepositoryImpl(get()) }
}
