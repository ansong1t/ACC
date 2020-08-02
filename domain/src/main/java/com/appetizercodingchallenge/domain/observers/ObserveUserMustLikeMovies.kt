package com.appetizercodingchallenge.domain.observers

import com.appetizercodingchallenge.data.entities.FeatureMovie
import com.appetizercodingchallenge.data.repositories.MovieRepository
import com.appetizercodingchallenge.domain.SubjectInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ObserveUserMustLikeMovies constructor(
    private val repository: MovieRepository
) : SubjectInteractor<ObserveUserMustLikeMovies.Params, List<FeatureMovie>>() {

    data class Params(val currentTrackId: Long, val collectionArtistId: Long)

    override fun createObservable(params: Params): Flow<List<FeatureMovie>> {
        return repository.observeUserMustLikeMovies(
            params.currentTrackId,
            params.collectionArtistId
        )
    }
}
