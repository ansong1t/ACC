package com.appetizercodingchallenge.data.datasources

import com.appetizercodingchallenge.data.entities.ErrorResult
import com.appetizercodingchallenge.data.entities.Result
import com.appetizercodingchallenge.data.responses.ItemResponse
import com.appetizercodingchallenge.data.services.ItemService
import com.appetizercodingchallenge.extensions.toResult

class GetItemDataSource(
    private val itemService: ItemService
) {
    suspend operator fun invoke(): Result<List<ItemResponse>> =
        try {
            itemService.getItems().toResult { response ->
                response.results!!
            }
        } catch (e: Exception) {
            ErrorResult(e)
        }
}
