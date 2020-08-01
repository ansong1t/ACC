package com.appetizercodingchallenge.domain.observers

import com.appetizercodingchallenge.data.entities.Song
import com.appetizercodingchallenge.data.repositories.SongRepository
import com.appetizercodingchallenge.domain.SubjectInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ObserveRelatedSongs constructor(
    private val repository: SongRepository
) : SubjectInteractor<ObserveRelatedSongs.Params, List<Song>>() {

    data class Params(val trackId: Long, val artistId: Long)

    override fun createObservable(params: Params): Flow<List<Song>> {
        return repository.observeRelatedSongs(params.trackId, params.artistId)
    }
}
