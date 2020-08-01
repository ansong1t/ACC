package com.appetizercodingchallenge.ui.songdetails

import com.appetizercodingchallenge.data.entities.Song

data class SongDetailsViewState(
    val isLoading: Boolean = true,
    val albumSongsLoading: Boolean = true,
    val songsRelatedLoading: Boolean = true,
    val song: Song = Song(),
    val relatedSongs: List<Song> = emptyList(),
    val albumSongs: List<Song> = emptyList()
)
