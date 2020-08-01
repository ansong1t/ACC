package com.appetizercodingchallenge.domain.observers

import com.appetizercodingchallenge.data.repositories.TvShowRepository
import com.appetizercodingchallenge.data.resultentities.TvShowWithEpisodes
import com.appetizercodingchallenge.domain.SubjectInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ObserveTvShowWithEpisodes constructor(
    private val repository: TvShowRepository
) : SubjectInteractor<ObserveTvShowWithEpisodes.Params, TvShowWithEpisodes>() {

    data class Params(val collectionId: Long)

    override fun createObservable(params: Params): Flow<TvShowWithEpisodes> {
        return repository.observeTvShowAndEpisodes(params.collectionId)
    }
}
