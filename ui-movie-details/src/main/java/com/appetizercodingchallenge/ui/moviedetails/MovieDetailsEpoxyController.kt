package com.appetizercodingchallenge.ui.moviedetails

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.appetizercodingchallenge.common.layout.headline2
import com.appetizercodingchallenge.common.layout.infiniteLoading
import com.appetizercodingchallenge.common.layout.separatorThin
import com.appetizercodingchallenge.common.layout.vertSpacerSmall
import com.appetizercodingchallenge.extensions.observable

internal class MovieDetailsEpoxyController(private var context: Context?) : EpoxyController() {
    var state: MovieDetailsViewState by observable(MovieDetailsViewState(), ::requestModelBuild)
    var callbacks: Callbacks? by observable(null, ::requestModelBuild)

    interface Callbacks {
        fun onTrackClicked(trackId: Long)
    }

    override fun buildModels() {
        headline2 {
            id("movie_headline")
            text("About")
        }

        description {
            id("description")
            text(state.movie.longDescription)
        }

        vertSpacerSmall {
            id("space_before_season_title")
        }

        if (state.isRelatedMoviesLoading) {
            infiniteLoading {
                id("infinite_loading_song_related")
            }
        } else state.relatedMovies.takeIf { it.isNotEmpty() }?.also {
            moviesSubtitleSeparator {
                id("title_related_movies_title")
                text(context?.getString(R.string.related_movies) ?: "Related Movies")
            }

            state.relatedMovies.forEach {

                movieItem {
                    id("episode_item_${it.id}")
                    counter(it.trackNumber)
                    title(it.trackName)
                    description(it.longDescription)
                    currency(it.currency)
                    price(it.trackPrice)
                    duration(it.trackTimeMillis)
                    imageUrl(it.artworkUrl100)
                    isCurrentTrack(it.id == state.movie.id)
                    onItemClick { _ ->
                        callbacks?.onTrackClicked(it.id)
                    }
                }

                separatorThin {
                    id("separator_${it.id}")
                }
            }
        }

        vertSpacerSmall {
            id("space_before_season_title")
        }

        if (state.isUserMustLikeMoviesLoading) {
            infiniteLoading {
                id("infinite_loading_song_related")
            }
        } else state.userMustLikeMovies.takeIf { it.isNotEmpty() }?.also {
            moviesSubtitleSeparator {
                id("title_movies_you_may_like_title")
                text(context?.getString(R.string.movies_you_may_like) ?: "Movies You May Like")
            }

            carousel {
                id("user_must_like_movie_carousels")
                numViewsToShowOnScreen(3.2f)
                models(
                    it.map { movie ->
                        MovieCarouselItemBindingModel_().apply {
                            id("carousel_item_${movie.id}")
                            imageUrl(movie.artworkUrl100)
                            trackName(movie.trackName)
                            dateReleased(movie.releaseDate)
                            onItemClicked { _ ->
                                callbacks?.onTrackClicked(movie.id)
                            }
                        }
                    }
                )
            }
        }

        if (state.isLoading) {
            infiniteLoading {
                id("infinite_loading_tv_show_details")
            }
            return
        }
    }

    fun removeCallback() {
        callbacks = null
    }

    fun clear() {
        context = null
    }
}
