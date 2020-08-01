package com.appetizercodingchallenge.data.mappers

import org.koin.dsl.module

val mapperModules = module {
    factory { ItemResponseToSongMapper() }
    factory { ItemResponseToFeatureMovieMapper() }
    factory { ItemResponseToTvEpisodeMapper() }
    factory { ItemResponseToTvShowMapper() }
}
