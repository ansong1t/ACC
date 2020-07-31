package com.appetizercodingchallenge.data.repositories

import androidx.paging.DataSource
import com.appetizercodingchallenge.data.daos.ItemDao
import com.appetizercodingchallenge.data.datasources.GetItemDataSource
import com.appetizercodingchallenge.data.datasources.SearchItemDataSource
import com.appetizercodingchallenge.data.resultentities.ItemEntryWithDetails
import com.appetizercodingchallenge.data.resultentities.SearchedItemEntryWithDetails

class ItemRepositoryImpl(
    private val searchItemDataSource: SearchItemDataSource,
    private val getItemDataSource: GetItemDataSource,
    private val itemDao: ItemDao
) : ItemRepository {
    override fun observePagedSearchedItems(): DataSource.Factory<Int, SearchedItemEntryWithDetails> =
        itemDao.getPagedSearchedItems()

    override fun observePagedItems(): DataSource.Factory<Int, ItemEntryWithDetails> =
        itemDao.getPagedItems()

    override suspend fun searchItems(searchKey: String) {
        val result = searchItemDataSource(searchKey)
        val items = result.getOrThrow()
        itemDao.withTransaction {
            itemDao.insertSearchedEvents(searchKey, items)
        }
    }

    override suspend fun updateItems() {
        val result = getItemDataSource()
        val items = result.getOrThrow()
        itemDao.insertItems(items)
    }
}
