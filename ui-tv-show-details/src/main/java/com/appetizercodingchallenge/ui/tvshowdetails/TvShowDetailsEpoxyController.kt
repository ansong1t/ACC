package com.appetizercodingchallenge.ui.tvshowdetails

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.appetizercodingchallenge.common.layout.headline2
import com.appetizercodingchallenge.common.layout.infiniteLoading
import com.appetizercodingchallenge.common.layout.separatorThin
import com.appetizercodingchallenge.common.layout.vertSpacerSmall
import com.appetizercodingchallenge.extensions.observable

internal class TvShowDetailsEpoxyController(private var context: Context?) : EpoxyController() {
    var state: TvShowDetailsViewState by observable(TvShowDetailsViewState(), ::requestModelBuild)
    var callbacks: Callbacks? by observable(null, ::requestModelBuild)

    interface Callbacks {
        fun onEpisodeViewMoreClicked(episodeUrl: String)
    }

    override fun buildModels() {
        headline2 {
            id("tv_show_headline")
            text("About")
        }

        description {
            id("description")
            text(state.tvShowWithEpisodes.longDescription())
        }

        vertSpacerSmall {
            id("space_before_season_title")
        }

        episodeTitle {
            id("title_seasons")
        }

        state.tvShowWithEpisodes.episodes.forEachIndexed { index, it ->

            episodeItem {
                id("episode_item_${it.id}")
                counter(it.trackNumber)
                title(it.trackName)
                description(it.longDescription)
                currency(it.currency)
                price(it.trackPrice)
                onViewMoreClick { v ->
                    callbacks?.onEpisodeViewMoreClicked(it.trackViewUrl)
                }
            }

            separatorThin {
                id("separator_${it.id}")
            }
        }

        vertSpacerSmall {
            id("space_before_season_title")
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
