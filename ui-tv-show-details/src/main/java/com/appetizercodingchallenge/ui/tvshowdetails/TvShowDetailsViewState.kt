package com.appetizercodingchallenge.ui.tvshowdetails

import com.appetizercodingchallenge.data.resultentities.TvShowWithEpisodes

data class TvShowDetailsViewState(
    val isLoading: Boolean = true,
    val tvShowWithEpisodes: TvShowWithEpisodes = TvShowWithEpisodes()
)
