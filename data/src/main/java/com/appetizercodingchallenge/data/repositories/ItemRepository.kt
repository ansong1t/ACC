package com.appetizercodingchallenge.data.repositories

import androidx.paging.DataSource
import com.appetizercodingchallenge.data.resultentities.ItemEntryWithDetails
import com.appetizercodingchallenge.data.resultentities.SearchedItemEntryWithDetails

interface ItemRepository {
    fun observePagedSearchedItems(): DataSource.Factory<Int, SearchedItemEntryWithDetails>
    fun observePagedItems(): DataSource.Factory<Int, ItemEntryWithDetails>
    suspend fun searchItems(searchKey: String = "")
    suspend fun updateItems()
}
