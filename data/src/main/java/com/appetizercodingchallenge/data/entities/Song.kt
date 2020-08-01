package com.appetizercodingchallenge.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    @ColumnInfo(name = "track_id") @PrimaryKey(autoGenerate = true) override val id: Long = 0,
    @ColumnInfo(name = "artist_id") val artistId: Long = 0,
    @ColumnInfo(name = "artist_name") val artistName: String = "",
    @ColumnInfo(name = "artist_view_url") val artistViewUrl: String = "",
    @ColumnInfo(name = "artwork_url_100") val artworkUrl100: String = "",
    @ColumnInfo(name = "artwork_url_30") val artworkUrl30: String = "",
    @ColumnInfo(name = "artwork_url_60") val artworkUrl60: String = "",
    @ColumnInfo(name = "collection_censored_name") val collectionCensoredName: String = "",
    @ColumnInfo(name = "collection_explicitness") val collectionExplicitness: String = "",
    @ColumnInfo(name = "collection_id") val collectionId: Long = 0,
    @ColumnInfo(name = "collection_name") val collectionName: String = "",
    @ColumnInfo(name = "collection_price") val collectionPrice: Double = 0.0,
    @ColumnInfo(name = "collection_view_url") val collectionViewUrl: String = "",
    @ColumnInfo(name = "country") val country: String = "",
    @ColumnInfo(name = "currency") val currency: String = "",
    @ColumnInfo(name = "disc_count") val discCount: Int = 0,
    @ColumnInfo(name = "disc_number") val discNumber: Int = 0,
    @ColumnInfo(name = "is_streamable") val isStreamable: Boolean = false,
    @ColumnInfo(name = "preview_url") val previewUrl: String = "",
    @ColumnInfo(name = "primary_genre_name") val primaryGenreName: String = "",
    @ColumnInfo(name = "release_date") val releaseDate: String = "",
    @ColumnInfo(name = "track_censored_name") val trackCensoredName: String = "",
    @ColumnInfo(name = "track_count") val trackCount: Int = 0,
    @ColumnInfo(name = "track_explicitness") val trackExplicitness: String = "",
    @ColumnInfo(name = "track_name") val trackName: String = "",
    @ColumnInfo(name = "track_number") val trackNumber: Int = 0,
    @ColumnInfo(name = "track_price") val trackPrice: Double = 0.0,
    @ColumnInfo(name = "track_time_millis") val trackTimeMillis: Long = 0,
    @ColumnInfo(name = "track_view_url") val trackViewUrl: String = "",
    @ColumnInfo(name = "wrapper_type") val wrapperType: String = ""
) : AccEntity {

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Song -> id == id
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + artistName.hashCode()
        result = 31 * result + wrapperType.hashCode()
        return result
    }
}
