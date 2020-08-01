package com.appetizercodingchallenge.data.types

enum class ListItemType(val type: String) {
    UNKNOWN("Unknown"),
    SONG("Song"),
    FEATURE_MOVIE("Feature Movie"),
    TV_SHOW("TV Show"),
    AUDIOBOOK("Audiobook")
}

fun toTrackType(value: String) =
    when (value) {
        ListItemType.SONG.type -> ListItemType.SONG
        ListItemType.FEATURE_MOVIE.type -> ListItemType.FEATURE_MOVIE
        ListItemType.TV_SHOW.type -> ListItemType.TV_SHOW
        ListItemType.AUDIOBOOK.type -> ListItemType.AUDIOBOOK
        else -> ListItemType.UNKNOWN
    }

fun responseToTrackType(value: String) =
    when (value) {
        "song" -> ListItemType.SONG
        "feature-movie" -> ListItemType.FEATURE_MOVIE
        "tv-episode" -> ListItemType.TV_SHOW
        "audiobook" -> ListItemType.AUDIOBOOK
        else -> ListItemType.UNKNOWN
    }
