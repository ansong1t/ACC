package com.appetizercodingchallenge.ui.audiobookdetails

import androidx.lifecycle.viewModelScope
import com.appetizercodingchallenge.ReduxViewModel
import com.appetizercodingchallenge.domain.observers.ObserveAudioBook
import com.appetizercodingchallenge.util.ObservableLoadingCounter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class AudioBookDetailsViewModel(
    collectionId: Long,
    observeAudioBook: ObserveAudioBook
) : ReduxViewModel<AudioBookDetailsViewState>(
    AudioBookDetailsViewState()
) {

    private val audioBookLoading = ObservableLoadingCounter()

    init {
        viewModelScope.launch {
            audioBookLoading.observable.collect { active ->
                setState { copy(isLoading = active) }
            }
        }

        viewModelScope.launch {
            observeAudioBook.observe()
                .collect {
                    setState { copy(audioBook = it) }
                    audioBookLoading.removeLoader()
                }
        }
        observeAudioBook(ObserveAudioBook.Params(collectionId))
        audioBookLoading.addLoader()
    }
}
