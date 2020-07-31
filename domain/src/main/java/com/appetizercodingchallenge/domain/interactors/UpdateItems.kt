package com.appetizercodingchallenge.domain.interactors

import com.appetizercodingchallenge.data.daos.ItemDao
import com.appetizercodingchallenge.data.repositories.ItemRepository
import com.appetizercodingchallenge.domain.Interactor
import com.appetizercodingchallenge.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class UpdateItems(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val repository: ItemRepository,
    private val itemDao: ItemDao
) : Interactor<UpdateItems.Params>() {

    data class Params(val page: Page, val forceRefresh: Boolean = false)

    enum class Page {
        NEXT_PAGE, REFRESH
    }

    override suspend fun doWork(params: Params) {
        withContext(appCoroutineDispatchers.io) {
            if (params.page == Page.REFRESH) {
                if (params.forceRefresh) {
                    itemDao.clearItemEntries()
                    repository.updateItems()
                } else if (!params.forceRefresh && !itemDao.hasItemEntry()) {
                    repository.updateItems()
                }
            } else if (params.page == Page.NEXT_PAGE) {
                // no action for now until API endpoint supports pagination
            }
        }
    }
}
