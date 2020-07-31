package com.appetizercodingchallenge.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "item_entries", foreignKeys = [
        ForeignKey(
            entity = Item::class,
            parentColumns = arrayOf("track_id"),
            childColumns = arrayOf("track_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["item_id"], unique = true)]
)
data class ItemEntry(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) override val id: Long = 0,
    @ColumnInfo(name = "item_id") val itemId: Long = 0,
    @ColumnInfo(name = "track_id") val trackId: Long = 0
) : AccEntity
