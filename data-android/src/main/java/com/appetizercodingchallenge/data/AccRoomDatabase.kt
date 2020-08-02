package com.appetizercodingchallenge.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.appetizercodingchallenge.data.converters.ItemConverter
import com.appetizercodingchallenge.data.entities.AudioBook
import com.appetizercodingchallenge.data.entities.FeatureMovie
import com.appetizercodingchallenge.data.entities.Song
import com.appetizercodingchallenge.data.entities.ItemEntry
import com.appetizercodingchallenge.data.entities.SearchedItemEntry
import com.appetizercodingchallenge.data.entities.TvEpisode
import com.appetizercodingchallenge.data.entities.TvShow
import dev.matrix.roomigrant.GenerateRoomMigrations

@Database(
    entities = [
        AudioBook::class,
        Song::class,
        FeatureMovie::class,
        TvShow::class,
        TvEpisode::class,
        ItemEntry::class,
        SearchedItemEntry::class
    ],
    version = 1
)
@TypeConverters(ItemConverter::class)
@GenerateRoomMigrations
abstract class AccRoomDatabase : RoomDatabase(), AccDatabase
