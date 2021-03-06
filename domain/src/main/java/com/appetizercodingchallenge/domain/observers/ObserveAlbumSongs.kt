package com.appetizercodingchallenge.domain.observers

import com.appetizercodingchallenge.data.entities.Song
import com.appetizercodingchallenge.data.repositories.SongRepository
import com.appetizercodingchallenge.domain.SubjectInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ObserveAlbumSongs constructor(
    private val repository: SongRepository
) : SubjectInteractor<ObserveAlbumSongs.Params, List<Song>>() {

    data class Params(val collectionId: Long)

    override fun createObservable(params: Params): Flow<List<Song>> {
        return repository.observeSongsByAlbum(params.collectionId)
    }
}
