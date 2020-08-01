package com.appetizercodingchallenge.data.repositories

import com.appetizercodingchallenge.data.resultentities.TvShowWithEpisodes
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun observeTvShowAndEpisodes(collectionId: Long): Flow<TvShowWithEpisodes>
}
