package com.appetizercodingchallenge.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audio_books")
data class AudioBook(
    @ColumnInfo(name = "collection_id") @PrimaryKey override val id: Long = 0,
    @ColumnInfo(name = "artist_id") val artistId: Long = 0,
    @ColumnInfo(name = "artist_name") val artistName: String = "",
    @ColumnInfo(name = "artist_view_url") val artistViewUrl: String = "",
    @ColumnInfo(name = "artwork_url_100") val artworkUrl100: String = "",
    @ColumnInfo(name = "collection_censored_name") val collectionCensoredName: String = "",
    @ColumnInfo(name = "collection_explicitness") val collectionExplicitness: String = "",
    @ColumnInfo(name = "collection_name") val collectionName: String = "",
    @ColumnInfo(name = "collection_price") val collectionPrice: Double = 0.0,
    @ColumnInfo(name = "collection_view_url") val collectionViewUrl: String = "",
    @ColumnInfo(name = "copyright") val copyright: String = "",
    @ColumnInfo(name = "country") val country: String = "",
    @ColumnInfo(name = "currency") val currency: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "preview_url") val previewUrl: String = "",
    @ColumnInfo(name = "primary_genre_name") val primaryGenreName: String = "",
    @ColumnInfo(name = "release_date") val releaseDate: String = "",
    @ColumnInfo(name = "track_count") val trackCount: Int = 0,
    @ColumnInfo(name = "wrapper_type") val wrapperType: String = ""
) : AccEntity {

    fun isExplicit(): Boolean = collectionExplicitness == "explicit"
}
