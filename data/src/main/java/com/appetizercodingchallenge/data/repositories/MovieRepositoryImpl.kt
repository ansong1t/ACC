package com.appetizercodingchallenge.data.repositories

import com.appetizercodingchallenge.data.daos.MovieDao
import com.appetizercodingchallenge.data.entities.FeatureMovie
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieDao: MovieDao
) : MovieRepository {

    override fun observeMovie(trackId: Long): Flow<FeatureMovie> = movieDao.getMovie(trackId)

    override fun observeRelatedMovies(
        collectionArtistId: Long
    ): Flow<List<FeatureMovie>> = movieDao.getRelatedMovies(collectionArtistId)

    override fun observeUserMustLikeMovies(
        trackId: Long,
        collectionArtistId: Long
    ): Flow<List<FeatureMovie>> = movieDao.getUserMustLikeMovies(trackId, collectionArtistId)
}
