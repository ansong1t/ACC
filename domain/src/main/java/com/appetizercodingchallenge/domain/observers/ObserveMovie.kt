package com.appetizercodingchallenge.domain.observers

import com.appetizercodingchallenge.data.entities.FeatureMovie
import com.appetizercodingchallenge.data.repositories.MovieRepository
import com.appetizercodingchallenge.domain.SubjectInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ObserveMovie constructor(
    private val repository: MovieRepository
) : SubjectInteractor<ObserveMovie.Params, FeatureMovie>() {

    data class Params(val trackId: Long)

    override fun createObservable(params: Params): Flow<FeatureMovie> {
        return repository.observeMovie(params.trackId)
    }
}
