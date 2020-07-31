package com.appetizercodingchallenge.data.datasources

import com.appetizercodingchallenge.data.entities.Item
import com.appetizercodingchallenge.data.entities.Result
import com.appetizercodingchallenge.data.mappers.SearchItemResponseToItem
import com.appetizercodingchallenge.data.services.ItemService
import com.appetizercodingchallenge.extensions.toResult

class GetItemDataSource(
    private val itemService: ItemService,
    private val searchItemResponseToItem: SearchItemResponseToItem
) {
    suspend operator fun invoke(): Result<List<Item>> =
        itemService.getItems().toResult { response ->
            response.results!!.map {
                searchItemResponseToItem(it)
            }
        }
}
