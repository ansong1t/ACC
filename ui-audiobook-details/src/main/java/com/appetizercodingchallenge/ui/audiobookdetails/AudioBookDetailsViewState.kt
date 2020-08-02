package com.appetizercodingchallenge.ui.audiobookdetails

import com.appetizercodingchallenge.data.entities.AudioBook

data class AudioBookDetailsViewState(
    val isLoading: Boolean = true,
    val audioBook: AudioBook = AudioBook()
)
