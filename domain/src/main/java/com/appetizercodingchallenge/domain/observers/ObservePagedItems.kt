package com.appetizercodingchallenge.domain.observers

import androidx.paging.PagedList
import com.appetizercodingchallenge.data.FlowPagedListBuilder
import com.appetizercodingchallenge.data.repositories.ItemRepository
import com.appetizercodingchallenge.data.resultentities.ItemEntryWithDetails
import com.appetizercodingchallenge.domain.PagingInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ObservePagedItems constructor(
    private val repository: ItemRepository
) : PagingInteractor<ObservePagedItems.Params, ItemEntryWithDetails>() {

    data class Params(
        override val pagingConfig: PagedList.Config,
        override val boundaryCallback: PagedList.BoundaryCallback<ItemEntryWithDetails>? = null
    ) : Parameters<ItemEntryWithDetails>

    override fun createObservable(params: Params): Flow<PagedList<ItemEntryWithDetails>> {
        return FlowPagedListBuilder(
            repository.observePagedItems(),
            params.pagingConfig,
            boundaryCallback = params.boundaryCallback
        ).buildFlow()
    }
}
