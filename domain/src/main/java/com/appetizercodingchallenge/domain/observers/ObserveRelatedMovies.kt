package com.appetizercodingchallenge.domain.observers

import com.appetizercodingchallenge.data.entities.FeatureMovie
import com.appetizercodingchallenge.data.repositories.MovieRepository
import com.appetizercodingchallenge.domain.SubjectInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ObserveRelatedMovies constructor(
    private val repository: MovieRepository
) : SubjectInteractor<ObserveRelatedMovies.Params, List<FeatureMovie>>() {

    data class Params(val collectionArtistId: Long)

    override fun createObservable(params: Params): Flow<List<FeatureMovie>> {
        return repository.observeRelatedMovies(params.collectionArtistId)
    }
}
