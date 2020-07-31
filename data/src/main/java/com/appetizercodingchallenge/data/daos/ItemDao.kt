package com.appetizercodingchallenge.data.daos

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.appetizercodingchallenge.data.entities.Item
import com.appetizercodingchallenge.data.entities.ItemEntry
import com.appetizercodingchallenge.data.entities.SearchedItemEntry
import com.appetizercodingchallenge.data.resultentities.ItemEntryWithDetails
import com.appetizercodingchallenge.data.resultentities.SearchedItemEntryWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ItemDao : EntityDao<Item>() {

    @Query("SELECT * FROM items WHERE id = :id")
    abstract fun get(id: Long): Item

    @Query("SELECT * FROM `items` WHERE id = :id")
    abstract fun getObservableItem(id: Long): Flow<Item?>

    @Transaction
    @Query("SELECT * FROM item_entries")
    abstract fun getPagedItems(): DataSource.Factory<Int, ItemEntryWithDetails>

    @Transaction
    @Query("SELECT * FROM searched_item_entries")
    abstract fun getPagedSearchedItems(): DataSource.Factory<Int, SearchedItemEntryWithDetails>

    @Query("DELETE FROM searched_item_entries")
    abstract suspend fun clearSearchResults()

    @Query("DELETE FROM item_entries")
    abstract suspend fun clearItemEntries()

    @Query("SELECT EXISTS (SELECT 1 FROM item_entries LIMIT 1)")
    abstract suspend fun hasItemEntry(): Boolean

    suspend fun insertSearchedEvents(searchKey: String, items: List<Item>) {
        items.forEach {
            val insertedId = insertOrUpdate(it)
            val entry = SearchedItemEntry(itemId = insertedId, searchKey = searchKey)
            insertSearchedItemEntry(entry)
        }
    }

    suspend fun insertItems(items: List<Item>) {
        items.forEach {
            val insertedId = insertWithReplace(it)
            val entry = ItemEntry(itemId = insertedId, trackId = it.trackId)
            insertItemEntry(entry)
        }
    }

    @Insert(entity = SearchedItemEntry::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSearchedItemEntry(entry: SearchedItemEntry)

    @Insert(entity = ItemEntry::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertItemEntry(entry: ItemEntry)
}
