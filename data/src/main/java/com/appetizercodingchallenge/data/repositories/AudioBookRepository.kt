package com.appetizercodingchallenge.data.repositories

import com.appetizercodingchallenge.data.entities.AudioBook
import kotlinx.coroutines.flow.Flow

interface AudioBookRepository {
    fun observeAudioBook(collectionId: Long): Flow<AudioBook>
}
