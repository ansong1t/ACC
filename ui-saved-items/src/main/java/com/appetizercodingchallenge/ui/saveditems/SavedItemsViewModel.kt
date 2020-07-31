package com.appetizercodingchallenge.ui.saveditems

import androidx.lifecycle.viewModelScope
import com.appetizercodingchallenge.api.UiStatus
import com.appetizercodingchallenge.base.InvokeStatus
import com.appetizercodingchallenge.domain.observers.ObservePagedSearchedItems
import com.appetizercodingchallenge.util.AppCoroutineDispatchers
import com.appetizercodingchallenge.common.paging.PagingViewModel
import com.appetizercodingchallenge.data.resultentities.SearchedItemEntryWithDetails
import com.appetizercodingchallenge.domain.interactors.UpdateSearchedItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class SavedItemsViewModel(
    override val dispatchers: AppCoroutineDispatchers,
    override val pagingInteractor: ObservePagedSearchedItems,
    private val interactor: UpdateSearchedItems
) : PagingViewModel<SavedItemsViewState, SearchedItemEntryWithDetails, ObservePagedSearchedItems>(
    state = SavedItemsViewState(),
    pageSize = 8
) {

    private val searchKey = MutableStateFlow("")

    init {
        pagingInteractor(
            ObservePagedSearchedItems.Params(
                pageListConfig,
                boundaryCallback
            )
        )

        launchObserves()
        viewModelScope.launch {
            searchKey.debounce(500).distinctUntilChanged()
                .collect {
                    refresh(fromUser = false)
                }
        }

        viewModelScope.launch {
            pagedList.collect {
                setState { copy(isEmpty = it.size == 0) }
            }
        }
    }

    fun search(key: String) {
        searchKey.value = key
    }

    fun refreshList() = refresh(fromUser = true, fullRefresh = false)

    override fun callRefresh(fromUser: Boolean): Flow<InvokeStatus> {
        return interactor(
            UpdateSearchedItems.Params(
                UpdateSearchedItems.Page.REFRESH,
                searchKey = searchKey.value
            )
        )
    }

    override fun callLoadMore(): Flow<InvokeStatus> {
        return interactor(
            UpdateSearchedItems.Params(
                UpdateSearchedItems.Page.NEXT_PAGE,
                searchKey = searchKey.value
            )
        )
    }

    override fun SavedItemsViewState.setLoaded(isLoaded: Boolean): SavedItemsViewState =
        copy(isLoaded = isLoaded)

    override fun SavedItemsViewState.setStatus(status: UiStatus): SavedItemsViewState =
        copy(status = status)
}
