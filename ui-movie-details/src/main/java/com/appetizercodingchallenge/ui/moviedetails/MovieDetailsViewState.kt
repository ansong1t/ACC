package com.appetizercodingchallenge.ui.moviedetails

import com.appetizercodingchallenge.data.entities.FeatureMovie

data class MovieDetailsViewState(
    val isLoading: Boolean = true,
    val isRelatedMoviesLoading: Boolean = true,
    val isUserMustLikeMoviesLoading: Boolean = true,
    val movie: FeatureMovie = FeatureMovie(),
    val relatedMovies: List<FeatureMovie> = emptyList(),
    val userMustLikeMovies: List<FeatureMovie> = emptyList()
)
