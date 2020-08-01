package com.appetizercodingchallenge.ui.tvshowdetails

import androidx.lifecycle.viewModelScope
import com.appetizercodingchallenge.ReduxViewModel
import com.appetizercodingchallenge.domain.observers.ObserveTvShowWithEpisodes
import com.appetizercodingchallenge.util.ObservableLoadingCounter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class TvShowDetailsViewModel(
    collectionId: Long,
    observeTvShowWithEpisodes: ObserveTvShowWithEpisodes
) : ReduxViewModel<TvShowDetailsViewState>(
    TvShowDetailsViewState()
) {

    private val loading = ObservableLoadingCounter()

    init {
        viewModelScope.launch {
            loading.observable.collect { active ->
                setState { copy(isLoading = active) }
            }
        }

        viewModelScope.launch {
            observeTvShowWithEpisodes.observe()
                .collect {
                    setState { copy(tvShowWithEpisodes = it) }
                    loading.removeLoader()
                }
        }
        observeTvShowWithEpisodes(ObserveTvShowWithEpisodes.Params(collectionId))
        loading.addLoader()
    }
}
