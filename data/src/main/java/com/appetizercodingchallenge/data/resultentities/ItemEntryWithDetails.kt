package com.appetizercodingchallenge.data.resultentities

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation
import com.appetizercodingchallenge.data.entities.Item
import com.appetizercodingchallenge.data.entities.ItemEntry
import java.util.Objects

data class ItemEntryWithDetails(
    @Embedded
    var itemEntry: ItemEntry = ItemEntry(),

    @Relation(
        parentColumn = "item_id",
        entityColumn = "id"
    )
    var item: Item? = Item()
) {

    @Ignore
    fun generateStableId(): Long {
        return Objects.hash(Item::class.java.name, itemEntry.id).toLong()
    }
}
