package com.appetizercodingchallenge.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appetizercodingchallenge.data.entities.Item
import com.appetizercodingchallenge.data.entities.ItemEntry
import com.appetizercodingchallenge.data.entities.SearchedItemEntry
import dev.matrix.roomigrant.GenerateRoomMigrations

@Database(
    entities = [
        Item::class,
        ItemEntry::class,
        SearchedItemEntry::class
    ],
    version = 2
)
@GenerateRoomMigrations
abstract class AccRoomDatabase : RoomDatabase(), AccDatabase
