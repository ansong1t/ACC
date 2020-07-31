package com.appetizercodingchallenge.domain.observers

import androidx.paging.PagedList
import com.appetizercodingchallenge.data.FlowPagedListBuilder
import com.appetizercodingchallenge.data.repositories.ItemRepository
import com.appetizercodingchallenge.data.resultentities.SearchedItemEntryWithDetails
import com.appetizercodingchallenge.domain.PagingInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ObservePagedSearchedItems constructor(
    private val repository: ItemRepository
) : PagingInteractor<ObservePagedSearchedItems.Params, SearchedItemEntryWithDetails>() {

    data class Params(
        override val pagingConfig: PagedList.Config,
        override val boundaryCallback: PagedList.BoundaryCallback<SearchedItemEntryWithDetails>? = null
    ) : Parameters<SearchedItemEntryWithDetails>

    override fun createObservable(params: Params): Flow<PagedList<SearchedItemEntryWithDetails>> {
        return FlowPagedListBuilder(
            repository.observePagedSearchedItems(),
            params.pagingConfig,
            boundaryCallback = params.boundaryCallback
        ).buildFlow()
    }
}
