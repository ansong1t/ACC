package com.appetizercodingchallenge.data.mappers

import com.appetizercodingchallenge.data.entities.Item
import com.appetizercodingchallenge.data.responses.ItemResponse

class SearchItemResponseToItem : Mapper<ItemResponse, Item> {
    override suspend operator fun invoke(from: ItemResponse): Item =
        Item(
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
            kind = from.kind,
            previewUrl = from.previewUrl,
            primaryGenreName = from.primaryGenreName,
            releaseDate = from.releaseDate,
            trackCensoredName = from.trackCensoredName,
            trackCount = from.trackCount,
            trackExplicitness = from.trackExplicitness,
            trackId = from.trackId,
            trackName = from.trackName,
            trackNumber = from.trackNumber,
            trackPrice = from.trackPrice,
            trackTimeMillis = from.trackTimeMillis,
            trackViewUrl = from.trackViewUrl,
            wrapperType = from.wrapperType
        )
}
