package com.appetizercodingchallenge.ui.moviedetails

import androidx.lifecycle.viewModelScope
import com.appetizercodingchallenge.ReduxViewModel
import com.appetizercodingchallenge.domain.observers.ObserveMovie
import com.appetizercodingchallenge.domain.observers.ObserveRelatedMovies
import com.appetizercodingchallenge.domain.observers.ObserveUserMustLikeMovies
import com.appetizercodingchallenge.util.ObservableLoadingCounter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class MovieDetailsViewModel(
    trackId: Long,
    observeMovie: ObserveMovie,
    observeRelatedMovies: ObserveRelatedMovies,
    observeUserMustLikeMovies: ObserveUserMustLikeMovies
) : ReduxViewModel<MovieDetailsViewState>(
    MovieDetailsViewState()
) {

    private val loading = ObservableLoadingCounter()
    private val relatedMoviesLoading = ObservableLoadingCounter()
    private val userMustLikeMoviesLoading = ObservableLoadingCounter()

    init {
        viewModelScope.launch {
            loading.observable.collect { active ->
                setState { copy(isLoading = active) }
            }
        }

        viewModelScope.launch {
            relatedMoviesLoading.observable.collect { active ->
                setState { copy(isRelatedMoviesLoading = active) }
            }
        }

        viewModelScope.launch {
            userMustLikeMoviesLoading.observable.collect { active ->
                setState { copy(isUserMustLikeMoviesLoading = active) }
            }
        }

        viewModelScope.launch {
            observeMovie.observe()
                .collect {
                    setState { copy(movie = it) }
                    loading.removeLoader()
                    observeRelatedMovies(ObserveRelatedMovies.Params(it.collectionArtistId))
                    observeUserMustLikeMovies(
                        ObserveUserMustLikeMovies.Params(it.id, it.collectionArtistId)
                    )
                }
        }
        observeMovie(ObserveMovie.Params(trackId))

        viewModelScope.launch {
            observeRelatedMovies.observe()
                .collect {
                    setState { copy(relatedMovies = it) }
                    relatedMoviesLoading.removeLoader()
                }
        }

        viewModelScope.launch {
            observeUserMustLikeMovies.observe()
                .collect {
                    setState { copy(userMustLikeMovies = it) }
                    userMustLikeMoviesLoading.removeLoader()
                }
        }

        loading.addLoader()
        relatedMoviesLoading.addLoader()
        userMustLikeMoviesLoading.addLoader()
    }
}
