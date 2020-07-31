package com.appetizercodingchallenge.data.datasources

import com.appetizercodingchallenge.data.entities.Item
import com.appetizercodingchallenge.data.entities.Result
import com.appetizercodingchallenge.data.mappers.SearchItemResponseToItem
import com.appetizercodingchallenge.data.services.ItemService
import com.appetizercodingchallenge.extensions.toResult

class SearchItemDataSource(
    private val itemService: ItemService,
    private val searchItemResponseToItem: SearchItemResponseToItem
) {
    suspend operator fun invoke(searchTerm: String): Result<List<Item>> =
        itemService.search(searchTerm).toResult { response ->
            response.results!!.map {
                searchItemResponseToItem(it)
            }
        }
}
