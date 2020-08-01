package com.appetizercodingchallenge.data.mappers

import com.appetizercodingchallenge.data.entities.TvEpisode
import com.appetizercodingchallenge.data.responses.ItemResponse

class ItemResponseToTvEpisodeMapper : Mapper<ItemResponse, TvEpisode> {
    override suspend operator fun invoke(from: ItemResponse): TvEpisode =
        TvEpisode(
            id = from.trackId,
            artistName = from.artistName,
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
            wrapperType = from.wrapperType,
            collectionArtistId = from.collectionArtistId,
            collectionHdPrice = from.collectionHdPrice,
            contentAdvisoryRating = from.contentAdvisoryRating,
            longDescription = from.longDescription,
            shortDescription = from.shortDescription,
            trackHdPrice = from.trackHdPrice,
            artistViewUrl = from.artistViewUrl
        )
}
