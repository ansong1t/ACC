package com.appetizercodingchallenge.ui.songdetails

import android.content.Context
import android.text.SpannableStringBuilder
import androidx.core.text.bold
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.appetizercodingchallenge.common.layout.headline3
import com.appetizercodingchallenge.common.layout.infiniteLoading
import com.appetizercodingchallenge.common.layout.vertSpacerSmall
import com.appetizercodingchallenge.common.layout.separatorThin
import com.appetizercodingchallenge.extensions.observable
import com.appetizercodingchallenge.util.formatDate
import com.appetizercodingchallenge.util.getDuration
import org.koin.core.KoinComponent

internal class SongDetailsEpoxyController(private var context: Context?) : EpoxyController(),
    KoinComponent {
    var state: SongDetailsViewState by observable(SongDetailsViewState(), ::requestModelBuild)
    var callbacks: Callbacks? by observable(null, ::requestModelBuild)

    interface Callbacks {
        fun onTrackClicked(trackId: Long)
    }

    override fun buildModels() {
        headline3 {
            id("song_artist_name")
            text("By %s".format(state.song.artistName))
        }

        songDescription {
            id("description")
            text(
                SpannableStringBuilder()
                    .append("Genre: ")
                    .bold { append(state.song.primaryGenreName) }
                    .append("\n")
                    .append("Release Date: ")
                    .bold { append(formatDate(state.song.releaseDate, outputFormat = "MMM yyyy")) }
                    .append("\n")
                    .append("Duration: ")
                    .bold { append(getDuration(state.song.trackTimeMillis)) }.toString()
            )
            imageUrl(state.song.artworkUrl100)
        }

        vertSpacerSmall {
            id("space_before_season_title")
        }

        if (state.albumSongsLoading) {
            infiniteLoading {
                id("infinite_loading_album_songs")
            }
        } else state.albumSongs.takeIf { it.count() > 1 }?.also {
            albumTitle {
                id("title_album")
                albumName("More Songs on Album %s".format(it[0].collectionName))
            }

            it.forEachIndexed { index, song ->
                separatorThin {
                    id("separator_${song.id}")
                }

                albumItem {
                    id("album_item${song.id}")
                    trackName(song.trackName)
                    trackNumber(song.trackNumber)
                    duration(getDuration(song.trackTimeMillis))
                    trackImageUrl(song.artworkUrl100)
                    isCurrentTrack(song.id == state.song.id)
                    onTrackOnClick { _ ->
                        callbacks?.onTrackClicked(song.id)
                    }
                }

                if (index == it.lastIndex) separatorThin {
                    id("separator_${song.id}")
                }
            }
        }

        if (state.songsRelatedLoading) {
            infiniteLoading {
                id("infinite_loading_song_related")
            }
        } else state.relatedSongs.takeIf { it.isNotEmpty() }?.also {
            albumTitle {
                id("title_album")
                albumName("More from %s".format(it[0].artistName))
            }

            carousel {
                id("related_songs_carousels")
                numViewsToShowOnScreen(3f)
                models(
                    it.map { song ->
                        SongCarouselItemBindingModel_().apply {
                            id("carousel_item_${song.id}")
                            imageUrl(song.artworkUrl100)
                            trackName(song.trackName)
                            dateReleased(
                                formatDate(song.releaseDate, outputFormat = "yyyy")
                            )
                            onItemClicked { _ ->
                                callbacks?.onTrackClicked(song.id)
                            }
                        }
                    }
                )
            }
        }

        if (state.isLoading) {
            infiniteLoading {
                id("infinite_loading_song_details")
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
