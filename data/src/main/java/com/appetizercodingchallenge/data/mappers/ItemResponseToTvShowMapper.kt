package com.appetizercodingchallenge.data.mappers

import com.appetizercodingchallenge.data.entities.TvShow
import com.appetizercodingchallenge.data.responses.ItemResponse

class ItemResponseToTvShowMapper : Mapper<ItemResponse, TvShow> {
    override suspend operator fun invoke(from: ItemResponse): TvShow =
        TvShow(
            id = from.collectionId,
            artworkUrl100 = from.artworkUrl100,
            collectionCensoredName = from.collectionCensoredName,
            collectionExplicitness = from.collectionExplicitness,
            collectionName = from.collectionName,
            collectionPrice = from.collectionPrice,
            collectionViewUrl = from.collectionViewUrl,
            contentAdvisoryRating = from.contentAdvisoryRating,
            currency = from.currency,
            discCount = from.discCount,
            discNumber = from.discNumber,
            collectionHdPrice = from.collectionHdPrice,
            primaryGenreName = from.primaryGenreName,
            longDescription = from.longDescription
        )
}
