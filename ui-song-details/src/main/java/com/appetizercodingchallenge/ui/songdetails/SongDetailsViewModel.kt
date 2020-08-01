package com.appetizercodingchallenge.ui.songdetails

import androidx.lifecycle.viewModelScope
import com.appetizercodingchallenge.ReduxViewModel
import com.appetizercodingchallenge.domain.observers.ObserveAlbumSongs
import com.appetizercodingchallenge.domain.observers.ObserveRelatedSongs
import com.appetizercodingchallenge.domain.observers.ObserveSong
import com.appetizercodingchallenge.util.ObservableLoadingCounter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class SongDetailsViewModel(
    trackId: Long,
    observeSong: ObserveSong,
    observeAlbumSongs: ObserveAlbumSongs,
    observeRelatedSongs: ObserveRelatedSongs
) : ReduxViewModel<SongDetailsViewState>(
    SongDetailsViewState()
) {

    private val songLoading = ObservableLoadingCounter()
    private val albumSongsLoading = ObservableLoadingCounter()
    private val songsRelatedLoading = ObservableLoadingCounter()

    init {
        viewModelScope.launch {
            songLoading.observable.collect { active ->
                setState { copy(isLoading = active) }
            }
        }
        viewModelScope.launch {
            songsRelatedLoading.observable.collect { active ->
                setState { copy(songsRelatedLoading = active) }
            }
        }
        viewModelScope.launch {
            albumSongsLoading.observable.collect { active ->
                setState { copy(albumSongsLoading = active) }
            }
        }

        viewModelScope.launch {
            observeSong.observe()
                .collect {
                    setState { copy(song = it) }
                    songLoading.removeLoader()

                    // get related songs after main song object received
                    observeAlbumSongs(ObserveAlbumSongs.Params(it.collectionId))

                    observeRelatedSongs(ObserveRelatedSongs.Params(it.id, it.artistId))
                }
        }
        observeSong(ObserveSong.Params(trackId))
        songLoading.addLoader()

        viewModelScope.launch {
            observeAlbumSongs.observe()
                .collect {
                    setState { copy(albumSongs = it) }
                    albumSongsLoading.removeLoader()
                }
        }
        albumSongsLoading.addLoader()

        viewModelScope.launch {
            observeRelatedSongs.observe()
                .collect {
                    setState { copy(relatedSongs = it) }
                    songsRelatedLoading.removeLoader()
                }
        }
        songsRelatedLoading.addLoader()
    }
}
