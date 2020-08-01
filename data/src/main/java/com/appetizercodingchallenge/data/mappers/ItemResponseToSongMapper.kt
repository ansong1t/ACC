package com.appetizercodingchallenge.data.mappers

import com.appetizercodingchallenge.data.entities.Song
import com.appetizercodingchallenge.data.responses.ItemResponse

class ItemResponseToSongMapper : Mapper<ItemResponse, Song> {
    override suspend operator fun invoke(from: ItemResponse): Song =
        Song(
            id = from.trackId,
            artistId = from.artistId,
            artistName = from.artistName,
            artistViewUrl = from.artistViewUrl,
            artworkUrl100 = from.artworkUrl100,
            artworkUrl30 = from.artworkUrl30,
            artworkUrl60 = from.artworkUrl60,
            collectionCensoredName = from.collectionCensoredName,
            collectionExplicitness = from.collectionExplicitness,
            collectionId = from.collectionId,
            collectionName = from.collectionName,
            collectionPrice = from.collectionPrice,
            collectionViewUrl = from.collectionViewUrl,
            country = from.country,
            currency = from.currency,
            discCount = from.discCount,
            discNumber = from.discNumber,
            isStreamable = from.isStreamable,
            previewUrl = from.previewUrl,
            primaryGenreName = from.primaryGenreName,
            releaseDate = from.releaseDate,
            trackCensoredName = from.trackCensoredName,
            trackCount = from.trackCount,
            trackExplicitness = from.trackExplicitness,
            trackName = from.trackName,
            trackNumber = from.trackNumber,
            trackPrice = from.trackPrice,
            trackTimeMillis = from.trackTimeMillis,
            trackViewUrl = from.trackViewUrl,
            wrapperType = from.wrapperType
        )
}
