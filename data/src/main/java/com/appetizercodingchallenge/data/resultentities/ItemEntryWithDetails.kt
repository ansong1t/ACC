package com.appetizercodingchallenge.data.resultentities

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation
import com.appetizercodingchallenge.data.entities.FeatureMovie
import com.appetizercodingchallenge.data.entities.Song
import com.appetizercodingchallenge.data.entities.ItemEntry
import com.appetizercodingchallenge.data.entities.TvShow
import com.appetizercodingchallenge.data.types.ListItemType
import java.util.Objects

data class ItemEntryWithDetails(
    @Embedded
    var itemEntry: ItemEntry = ItemEntry(),

    @Relation(
        parentColumn = "track_id",
        entityColumn = "track_id"
    )
    var song: Song? = Song(),

    @Relation(
        parentColumn = "track_id",
        entityColumn = "track_id"
    )
    var featureMovie: FeatureMovie? = null,

    @Relation(
        parentColumn = "track_id",
        entityColumn = "collection_id"
    )
    var tvShow: TvShow? = null
) {

    @Ignore
    fun generateStableId(): Long {
        return Objects.hash(Song::class.java.name, FeatureMovie::class.java.name, itemEntry.trackId)
            .toLong()
    }

    @Ignore
    fun trackName(): String = when (itemEntry.kind) {
        ListItemType.SONG -> song!!.trackName
        ListItemType.FEATURE_MOVIE -> featureMovie!!.trackName
        ListItemType.TV_SHOW -> tvShow!!.collectionName
        else -> "No track Name"
    }

    @Ignore
    fun genre(): String = when (itemEntry.kind) {
        ListItemType.SONG -> song!!.primaryGenreName
        ListItemType.FEATURE_MOVIE -> featureMovie!!.primaryGenreName
        ListItemType.TV_SHOW -> tvShow!!.primaryGenreName
        else -> "No Genre"
    }

    @Ignore
    fun imageUrl(): String = when (itemEntry.kind) {
        ListItemType.SONG -> song!!.artworkUrl100
        ListItemType.FEATURE_MOVIE -> featureMovie!!.artworkUrl100
        ListItemType.TV_SHOW -> tvShow!!.artworkUrl100
        else -> ""
    }

    @Ignore
    fun trackPrice(): Double = when (itemEntry.kind) {
        ListItemType.SONG -> song!!.trackPrice
        ListItemType.FEATURE_MOVIE -> featureMovie!!.trackPrice
        ListItemType.TV_SHOW -> tvShow!!.collectionPrice
        else -> 0.0
    }

    @Ignore
    fun currency(): String = when (itemEntry.kind) {
        ListItemType.SONG -> song!!.currency
        ListItemType.FEATURE_MOVIE -> featureMovie!!.currency
        ListItemType.TV_SHOW -> tvShow!!.currency
        else -> ""
    }

    @Ignore
    fun kind(): String = itemEntry.kind.type
}
