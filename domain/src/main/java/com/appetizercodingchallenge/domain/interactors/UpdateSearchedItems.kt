package com.appetizercodingchallenge.domain.interactors

import com.appetizercodingchallenge.data.daos.ItemDao
import com.appetizercodingchallenge.data.repositories.ItemRepository
import com.appetizercodingchallenge.domain.Interactor
import com.appetizercodingchallenge.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class UpdateSearchedItems(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val repository: ItemRepository,
    private val itemDao: ItemDao
) : Interactor<UpdateSearchedItems.Params>() {

    data class Params(val page: Page, val searchKey: String)

    enum class Page {
        NEXT_PAGE, REFRESH
    }

    override suspend fun doWork(params: Params) {
        withContext(appCoroutineDispatchers.io) {
            if (params.page == Page.REFRESH) {
//                itemDao.clearSearchResults()
                if (params.searchKey.isNotEmpty()) repository.searchItems(params.searchKey)
            } else if (params.page == Page.NEXT_PAGE) {
                // no action for now until API endpoint supports pagination
            }
        }
    }
}
