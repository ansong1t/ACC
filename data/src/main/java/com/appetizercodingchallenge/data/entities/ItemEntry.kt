package com.appetizercodingchallenge.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.appetizercodingchallenge.data.types.ListItemType

@Entity(
    tableName = "item_entries",
    indices = [Index(value = ["item_id"], unique = true)]
)
data class ItemEntry(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) override val id: Long = 0,
    @ColumnInfo(name = "item_id") val itemId: Long = 0,
    @ColumnInfo(name = "kind") val kind: ListItemType = ListItemType.UNKNOWN
) : AccEntity
