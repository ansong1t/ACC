package com.appetizercodingchallenge.data.daos

import androidx.room.Dao
import androidx.room.Query
import com.appetizercodingchallenge.data.entities.FeatureMovie
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDao : EntityDao<FeatureMovie>() {

    @Query("SELECT * FROM feature_movies WHERE track_id = :trackId")
    abstract fun getMovie(trackId: Long): Flow<FeatureMovie>

    @Query(
        "SELECT * FROM feature_movies " +
                "WHERE collection_artist_id = :collectionArtistsId " +
                "ORDER BY track_number"
    )
    abstract fun getRelatedMovies(collectionArtistsId: Long): Flow<List<FeatureMovie>>

    @Query(
        "SELECT * FROM feature_movies " +
                "WHERE collection_artist_id != :collectionArtistsId " +
                "AND track_id != :notIncludedTrackId"
    )
    abstract fun getUserMustLikeMovies(
        notIncludedTrackId: Long,
        collectionArtistsId: Long
    ): Flow<List<FeatureMovie>>
}
