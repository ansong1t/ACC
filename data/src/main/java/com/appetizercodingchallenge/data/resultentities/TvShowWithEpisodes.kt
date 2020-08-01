package com.appetizercodingchallenge.data.resultentities

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation
import com.appetizercodingchallenge.data.entities.TvEpisode
import com.appetizercodingchallenge.data.entities.TvShow

data class TvShowWithEpisodes(
    @Embedded
    var tvShow: TvShow = TvShow(),

    @Relation(
        parentColumn = "collection_id",
        entityColumn = "collection_id"
    )
    var episodes: List<TvEpisode> = emptyList()
) {

    @Ignore
    fun imageUrl(): String = tvShow.artworkUrl100

    @Ignore
    fun longDescription(): String = tvShow.longDescription

    @Ignore
    fun tvShowName(): String = tvShow.collectionName

    @Ignore
    fun currency(): String = tvShow.currency

    @Ignore
    fun collectionPrice(): Double = tvShow.collectionPrice

    @Ignore
    fun genre(): String = tvShow.primaryGenreName

    @Ignore
    fun contentAdvisory(): String = tvShow.contentAdvisoryRating
}
