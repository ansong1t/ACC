package com.appetizercodingchallenge.domain.observers

import com.appetizercodingchallenge.data.entities.Song
import com.appetizercodingchallenge.data.repositories.SongRepository
import com.appetizercodingchallenge.domain.SubjectInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ObserveSong constructor(
    private val repository: SongRepository
) : SubjectInteractor<ObserveSong.Params, Song>() {

    data class Params(val trackId: Long)

    override fun createObservable(params: Params): Flow<Song> {
        return repository.observeSong(params.trackId)
    }
}
