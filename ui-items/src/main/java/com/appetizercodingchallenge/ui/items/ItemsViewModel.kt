package com.appetizercodingchallenge.ui.items

import com.appetizercodingchallenge.api.UiStatus
import com.appetizercodingchallenge.base.InvokeStatus
import com.appetizercodingchallenge.common.paging.PagingViewModel
import com.appetizercodingchallenge.data.resultentities.ItemEntryWithDetails
import com.appetizercodingchallenge.domain.interactors.UpdateItems
import com.appetizercodingchallenge.domain.observers.ObservePagedItems
import com.appetizercodingchallenge.util.AppCoroutineDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class ItemsViewModel(
    override val dispatchers: AppCoroutineDispatchers,
    override val pagingInteractor: ObservePagedItems,
    private val interactor: UpdateItems
) : PagingViewModel<ItemsViewState, ItemEntryWithDetails, ObservePagedItems>(
    state = ItemsViewState(),
    pageSize = 6
) {

    init {
        pagingInteractor(
            ObservePagedItems.Params(
                pageListConfig,
                boundaryCallback
            )
        )

        launchObserves()

        refresh(fromUser = false, fullRefresh = false)
    }

    override fun callRefresh(fromUser: Boolean): Flow<InvokeStatus> {
        return interactor(UpdateItems.Params(UpdateItems.Page.REFRESH, fromUser))
    }

    override fun callLoadMore(): Flow<InvokeStatus> {
        return interactor(UpdateItems.Params(UpdateItems.Page.NEXT_PAGE))
    }

    override fun ItemsViewState.setLoaded(isLoaded: Boolean): ItemsViewState =
        copy(isLoaded = isLoaded)

    override fun ItemsViewState.setStatus(status: UiStatus): ItemsViewState =
        copy(status = status)
}
