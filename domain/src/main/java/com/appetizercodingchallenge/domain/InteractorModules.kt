package com.appetizercodingchallenge.domain

import com.appetizercodingchallenge.domain.interactors.UpdateItems
import com.appetizercodingchallenge.domain.interactors.UpdateSearchedItems
import com.appetizercodingchallenge.domain.observers.ObserveAlbumSongs
import com.appetizercodingchallenge.domain.observers.ObserveAudioBook
import com.appetizercodingchallenge.domain.observers.ObserveMovie
import com.appetizercodingchallenge.domain.observers.ObservePagedItems
import com.appetizercodingchallenge.domain.observers.ObservePagedSearchedItems
import com.appetizercodingchallenge.domain.observers.ObserveRelatedMovies
import com.appetizercodingchallenge.domain.observers.ObserveRelatedSongs
import com.appetizercodingchallenge.domain.observers.ObserveSong
import com.appetizercodingchallenge.domain.observers.ObserveTvShowWithEpisodes
import com.appetizercodingchallenge.domain.observers.ObserveUserMustLikeMovies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val interactorModules = module {
    factory { UpdateSearchedItems(get(), get(), get()) }
    factory { UpdateItems(get(), get(), get()) }

    factory { ObservePagedSearchedItems(get()) }
    factory { ObservePagedItems(get()) }
    factory { ObserveTvShowWithEpisodes(get()) }
    factory { ObserveSong(get()) }
    factory { ObserveAlbumSongs(get()) }
    factory { ObserveRelatedSongs(get()) }
    factory { ObserveMovie(get()) }
    factory { ObserveRelatedMovies(get()) }
    factory { ObserveUserMustLikeMovies(get()) }
    factory { ObserveAudioBook(get()) }
}
