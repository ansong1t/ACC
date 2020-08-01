package com.appetizercodingchallenge.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tv_episodes", foreignKeys = [
        ForeignKey(
            entity = TvShow::class,
            parentColumns = arrayOf("collection_id"),
            childColumns = arrayOf("collection_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TvEpisode(
    @ColumnInfo(name = "track_id") @PrimaryKey override val id: Long = 0,
    @ColumnInfo(name = "artist_name") val artistName: String = "",
    @ColumnInfo(name = "artist_view_url") val artistViewUrl: String = "",
    @ColumnInfo(name = "artwork_url100") val artworkUrl100: String = "",
    @ColumnInfo(name = "artwork_url30") val artworkUrl30: String = "",
    @ColumnInfo(name = "artwork_url60") val artworkUrl60: String = "",
    @ColumnInfo(name = "collection_censored_name") val collectionCensoredName: String = "",
    @ColumnInfo(name = "collection_artist_id") val collectionArtistId: Long = 0,
    @ColumnInfo(name = "collection_explicitness") val collectionExplicitness: String = "",
    @ColumnInfo(name = "collection_hd_price") val collectionHdPrice: Double = 0.0,
    @ColumnInfo(name = "collection_id") val collectionId: Long = 0,
    @ColumnInfo(name = "collection_name") val collectionName: String = "",
    @ColumnInfo(name = "collection_price") val collectionPrice: Double = 0.0,
    @ColumnInfo(name = "collection_view_url") val collectionViewUrl: String = "",
    @ColumnInfo(name = "content_advisory_rating") val contentAdvisoryRating: String = "",
    @ColumnInfo(name = "country") val country: String = "",
    @ColumnInfo(name = "currency") val currency: String = "",
    @ColumnInfo(name = "disc_count") val discCount: Int = 0,
    @ColumnInfo(name = "disc_number") val discNumber: Int = 0,
    @ColumnInfo(name = "long_description") val longDescription: String = "",
    @ColumnInfo(name = "preview_url") val previewUrl: String = "",
    @ColumnInfo(name = "primary_genre_name") val primaryGenreName: String = "",
    @ColumnInfo(name = "releaseDate") val releaseDate: String = "",
    @ColumnInfo(name = "short_description") val shortDescription: String = "",
    @ColumnInfo(name = "track_censored_name") val trackCensoredName: String = "",
    @ColumnInfo(name = "track_count") val trackCount: Int = 0,
    @ColumnInfo(name = "track_explicitness") val trackExplicitness: String = "",
    @ColumnInfo(name = "track_hd_price") val trackHdPrice: Double = 0.0,
    @ColumnInfo(name = "track_name") val trackName: String = "",
    @ColumnInfo(name = "track_number") val trackNumber: Int = 0,
    @ColumnInfo(name = "track_price") val trackPrice: Double = 0.0,
    @ColumnInfo(name = "track_time_millis") val trackTimeMillis: Long = 0,
    @ColumnInfo(name = "track_view_url") val trackViewUrl: String = "",
    @ColumnInfo(name = "wrapper_type") val wrapperType: String = ""
) : AccEntity
