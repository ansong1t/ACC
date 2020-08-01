package com.appetizercodingchallenge.data.mappers

import com.appetizercodingchallenge.data.entities.FeatureMovie
import com.appetizercodingchallenge.data.responses.ItemResponse

class ItemResponseToFeatureMovieMapper : Mapper<ItemResponse, FeatureMovie> {
    override suspend operator fun invoke(from: ItemResponse): FeatureMovie =
        FeatureMovie(
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
            collectionArtistViewUrl = from.collectionArtistViewUrl,
            collectionHdPrice = from.collectionHdPrice,
            contentAdvisoryRating = from.contentAdvisoryRating,
            hasITunesExtras = from.hasITunesExtras,
            longDescription = from.longDescription,
            shortDescription = from.shortDescription,
            trackHdPrice = from.trackHdPrice,
            trackHdRentalPrice = from.trackHdRentalPrice,
            trackRentalPrice = from.trackRentalPrice
        )
}
