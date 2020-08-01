package com.appetizercodingchallenge.domain

import com.appetizercodingchallenge.domain.interactors.UpdateItems
import com.appetizercodingchallenge.domain.interactors.UpdateSearchedItems
import com.appetizercodingchallenge.domain.observers.ObservePagedItems
import com.appetizercodingchallenge.domain.observers.ObservePagedSearchedItems
import com.appetizercodingchallenge.domain.observers.ObserveTvShowWithEpisodes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val interactorModules = module {
    factory { UpdateSearchedItems(get(), get(), get()) }
    factory { UpdateItems(get(), get(), get()) }

    factory { ObservePagedSearchedItems(get()) }
    factory { ObservePagedItems(get()) }
    factory { ObserveTvShowWithEpisodes(get()) }
}
