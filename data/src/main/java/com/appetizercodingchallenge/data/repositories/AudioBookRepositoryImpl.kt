package com.appetizercodingchallenge.data.repositories

import com.appetizercodingchallenge.data.daos.AudioBookDao
import com.appetizercodingchallenge.data.entities.AudioBook
import kotlinx.coroutines.flow.Flow

class AudioBookRepositoryImpl(
    private val audioBookDao: AudioBookDao
) : AudioBookRepository {

    override fun observeAudioBook(collectionId: Long): Flow<AudioBook> =
        audioBookDao.getAudioBook(collectionId)
}
