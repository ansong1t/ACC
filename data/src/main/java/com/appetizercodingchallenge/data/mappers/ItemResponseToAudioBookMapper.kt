package com.appetizercodingchallenge.data.mappers

import com.appetizercodingchallenge.data.entities.AudioBook
import com.appetizercodingchallenge.data.responses.ItemResponse

class ItemResponseToAudioBookMapper : Mapper<ItemResponse, AudioBook> {
    override suspend operator fun invoke(from: ItemResponse): AudioBook =
        AudioBook(
            id = from.collectionId,
            artistName = from.artistName,
            artworkUrl100 = from.artworkUrl100,
            collectionCensoredName = from.collectionCensoredName,
            collectionExplicitness = from.collectionExplicitness,
            collectionName = from.collectionName,
            collectionPrice = from.collectionPrice,
            collectionViewUrl = from.collectionViewUrl,
            country = from.country,
            currency = from.currency,
            previewUrl = from.previewUrl,
            primaryGenreName = from.primaryGenreName,
            releaseDate = from.releaseDate,
            trackCount = from.trackCount,
            wrapperType = from.wrapperType,
            artistViewUrl = from.artistViewUrl,
            description = from.description,
            artistId = from.artistId,
            copyright = from.copyright
        )
}
