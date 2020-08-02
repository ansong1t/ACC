package com.appetizercodingchallenge.ui.audiobookdetails

import android.content.Context
import android.text.SpannableStringBuilder
import androidx.core.text.bold
import com.airbnb.epoxy.EpoxyController
import com.appetizercodingchallenge.common.layout.headline3
import com.appetizercodingchallenge.common.layout.infiniteLoading
import com.appetizercodingchallenge.common.layout.vertSpacerSmall
import com.appetizercodingchallenge.extensions.observable
import com.appetizercodingchallenge.util.formatDate
import org.koin.core.KoinComponent

internal class AudioBookDetailsEpoxyController(private var context: Context?) : EpoxyController(),
    KoinComponent {
    var state: AudioBookDetailsViewState by observable(
        AudioBookDetailsViewState(),
        ::requestModelBuild
    )
    var callbacks: Callbacks? by observable(null, ::requestModelBuild)

    interface Callbacks {
        fun onPreviewUrl(url: String)
    }

    override fun buildModels() {
        headline3 {
            id("audioBook_artist_name")
            text("Audio Book By ${state.audioBook.artistName}")
        }

        audioBookDescription {
            id("description")
            text(
                SpannableStringBuilder()
                    .append("Genre: ")
                    .bold { append(state.audioBook.primaryGenreName) }
                    .append("\n")
                    .append("Release Date: ")
                    .bold {
                        append(formatDate(state.audioBook.releaseDate, outputFormat = "MMM yyyy"))
                    }
            )
            description(state.audioBook.description)
            imageUrl(state.audioBook.artworkUrl100)
            onViewPreviewClick { _ ->
                callbacks?.onPreviewUrl(state.audioBook.previewUrl)
            }
        }

        headline3 {
            id("audio_book_about_title")
            text("About")
        }

        audioBookAbout {
            id("audio_book_about")
            text(state.audioBook.description)
        }

        vertSpacerSmall {
            id("space_bottom")
        }

        if (state.isLoading) {
            infiniteLoading {
                id("infinite_loading_audioBook_details")
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
