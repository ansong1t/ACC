package com.appetizercodingchallenge.data.repositories

import androidx.paging.DataSource
import com.appetizercodingchallenge.data.daos.ItemDao
import com.appetizercodingchallenge.data.datasources.GetItemDataSource
import com.appetizercodingchallenge.data.mappers.ItemResponseToFeatureMovieMapper
import com.appetizercodingchallenge.data.mappers.ItemResponseToSongMapper
import com.appetizercodingchallenge.data.mappers.ItemResponseToTvEpisodeMapper
import com.appetizercodingchallenge.data.mappers.ItemResponseToTvShowMapper
import com.appetizercodingchallenge.data.resultentities.ItemEntryWithDetails
import com.appetizercodingchallenge.data.resultentities.SearchedItemEntryWithDetails

class ItemRepositoryImpl(
    private val getItemDataSource: GetItemDataSource,
    private val itemDao: ItemDao,
    private val itemResponseToSongMapper: ItemResponseToSongMapper,
    private val itemResponseToFeatureMovieMapper: ItemResponseToFeatureMovieMapper,
    private val itemResponseToTvEpisodeMapper: ItemResponseToTvEpisodeMapper,
    private val itemResponseToTvShowMapper: ItemResponseToTvShowMapper
) : ItemRepository {

    override fun observePagedSearchedItems(): DataSource.Factory<Int, SearchedItemEntryWithDetails> =
        itemDao.getPagedSearchedItems()

    override fun observePagedItems(): DataSource.Factory<Int, ItemEntryWithDetails> =
        itemDao.getPagedItems()

    override suspend fun searchItems(searchKey: String) {
        // TODO
    }

    override suspend fun updateItems() {
        val result = getItemDataSource()
        val items = result.getOrThrow()
        itemDao.insertItems(
            items,
            itemResponseToSongMapper,
            itemResponseToFeatureMovieMapper,
            itemResponseToTvEpisodeMapper,
            itemResponseToTvShowMapper
        )
    }
}
