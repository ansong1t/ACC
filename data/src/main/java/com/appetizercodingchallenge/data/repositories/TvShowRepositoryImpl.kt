package com.appetizercodingchallenge.data.repositories

import com.appetizercodingchallenge.data.daos.TvShowDao
import com.appetizercodingchallenge.data.resultentities.TvShowWithEpisodes
import kotlinx.coroutines.flow.Flow

class TvShowRepositoryImpl(
    private val tvShowDao: TvShowDao
) : TvShowRepository {

    override fun observeTvShowAndEpisodes(collectionId: Long): Flow<TvShowWithEpisodes> {
        return tvShowDao.getTvShowWithEpisodes(collectionId)
    }
}
