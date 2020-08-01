package com.appetizercodingchallenge.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows")
data class TvShow(
    @ColumnInfo(name = "collection_id") @PrimaryKey override val id: Long = 0,
    @ColumnInfo(name = "artwork_url100") val artworkUrl100: String = "",
    @ColumnInfo(name = "collection_censored_name") val collectionCensoredName: String = "",
    @ColumnInfo(name = "collection_explicitness") val collectionExplicitness: String = "",
    @ColumnInfo(name = "collection_hd_price") val collectionHdPrice: Double = 0.0,
    @ColumnInfo(name = "collection_name") val collectionName: String = "",
    @ColumnInfo(name = "collection_price") val collectionPrice: Double = 0.0,
    @ColumnInfo(name = "collection_view_url") val collectionViewUrl: String = "",
    @ColumnInfo(name = "content_advisory_rating") val contentAdvisoryRating: String = "",
    @ColumnInfo(name = "currency") val currency: String = "",
    @ColumnInfo(name = "disc_count") val discCount: Int = 0,
    @ColumnInfo(name = "disc_number") val discNumber: Int = 0,
    @ColumnInfo(name = "long_description") val longDescription: String = "",
    @ColumnInfo(name = "primary_genre_name") val primaryGenreName: String = ""
) : AccEntity
