package com.appetizercodingchallenge.domain.observers

import com.appetizercodingchallenge.data.entities.AudioBook
import com.appetizercodingchallenge.data.repositories.AudioBookRepository
import com.appetizercodingchallenge.domain.SubjectInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ObserveAudioBook constructor(
    private val repository: AudioBookRepository
) : SubjectInteractor<ObserveAudioBook.Params, AudioBook>() {

    data class Params(val collectionId: Long)

    override fun createObservable(params: Params): Flow<AudioBook> {
        return repository.observeAudioBook(params.collectionId)
    }
}
