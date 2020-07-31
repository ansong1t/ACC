package com.appetizercodingchallenge.data.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemResponse(
    @Json(name = "artistId")
    val artistId: Long = 0,
    @Json(name = "artistName")
    val artistName: String = "",
    @Json(name = "artistViewUrl")
    val artistViewUrl: String = "",
    @Json(name = "artworkUrl100")
    val artworkUrl100: String = "",
    @Json(name = "artworkUrl30")
    val artworkUrl30: String = "",
    @Json(name = "artworkUrl60")
    val artworkUrl60: String = "",
    @Json(name = "collectionCensoredName")
    val collectionCensoredName: String = "",
    @Json(name = "collectionExplicitness")
    val collectionExplicitness: String = "",
    @Json(name = "collectionId")
    val collectionId: Long = 0,
    @Json(name = "collectionName")
    val collectionName: String = "",
    @Json(name = "collectionPrice")
    val collectionPrice: Double = 0.0,
    @Json(name = "collectionViewUrl")
    val collectionViewUrl: String = "",
    @Json(name = "country")
    val country: String = "",
    @Json(name = "currency")
    val currency: String = "",
    @Json(name = "discCount")
    val discCount: Int = 0,
    @Json(name = "discNumber")
    val discNumber: Int = 0,
    @Json(name = "isStreamable")
    val isStreamable: Boolean = false,
    @Json(name = "kind")
    val kind: String = "",
    @Json(name = "previewUrl")
    val previewUrl: String = "",
    @Json(name = "primaryGenreName")
    val primaryGenreName: String = "",
    @Json(name = "releaseDate")
    val releaseDate: String = "",
    @Json(name = "trackCensoredName")
    val trackCensoredName: String = "",
    @Json(name = "trackCount")
    val trackCount: Int = 0,
    @Json(name = "trackExplicitness")
    val trackExplicitness: String = "",
    @Json(name = "trackId")
    val trackId: Long = 0,
    @Json(name = "trackName")
    val trackName: String = "",
    @Json(name = "trackNumber")
    val trackNumber: Int = 0,
    @Json(name = "trackPrice")
    val trackPrice: Double = 0.0,
    @Json(name = "trackTimeMillis")
    val trackTimeMillis: Long = 0,
    @Json(name = "trackViewUrl")
    val trackViewUrl: String = "",
    @Json(name = "wrapperType")
    val wrapperType: String = ""
)
