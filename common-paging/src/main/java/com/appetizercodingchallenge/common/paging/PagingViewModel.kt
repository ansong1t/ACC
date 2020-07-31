package com.appetizercodingchallenge.common.paging

import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.appetizercodingchallenge.ReduxViewModel
import com.appetizercodingchallenge.api.UiError
import com.appetizercodingchallenge.api.UiIdle
import com.appetizercodingchallenge.api.UiLoading
import com.appetizercodingchallenge.api.UiStatus
import com.appetizercodingchallenge.api.UiSuccess
import com.appetizercodingchallenge.base.InvokeError
import com.appetizercodingchallenge.base.InvokeStarted
import com.appetizercodingchallenge.base.InvokeStatus
import com.appetizercodingchallenge.base.InvokeSuccess
import com.appetizercodingchallenge.domain.PagingInteractor
import com.appetizercodingchallenge.util.AppCoroutineDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
abstract class PagingViewModel<EVS : PagingViewState, LI, PI : PagingInteractor<*, LI>>(
    state: EVS,
    private val pageSize: Int = 21 // depends on size of item visible on screen
) : ReduxViewModel<EVS>(
    state
) {
    protected abstract val dispatchers: AppCoroutineDispatchers
    protected abstract val pagingInteractor: PI

    private val messages = MutableStateFlow<UiStatus>(UiIdle)
    private val loaded = MutableStateFlow(false)

    val pagedList: Flow<PagedList<LI>>
        get() = pagingInteractor.observe()

    protected val pageListConfig = PagedList.Config.Builder().run {
        setPageSize(pageSize * 3)
        setPrefetchDistance(pageSize)
        setEnablePlaceholders(false)
        build()
    }

    protected val boundaryCallback = object : PagedList.BoundaryCallback<LI>() {
        override fun onItemAtEndLoaded(itemAtEnd: LI) = onListScrolledToEnd()

        override fun onItemAtFrontLoaded(itemAtFront: LI) {
            loaded.value = true
        }

        override fun onZeroItemsLoaded() {
            loaded.value = true
        }
    }

    protected fun launchObserves() {
        viewModelScope.launch {
            messages.collectAndSetState { setStatus(it) }
        }

        viewModelScope.launch {
            loaded.collectAndSetState { setLoaded(it) }
        }
    }

    abstract fun EVS.setLoaded(isLoaded: Boolean): EVS
    abstract fun EVS.setStatus(status: UiStatus): EVS

    fun onListScrolledToEnd() {
        viewModelScope.launch {
            callLoadMore()
                .catch { messages.value = UiError(it) }
                .map {
                    when (it) {
                        InvokeSuccess -> UiSuccess
                        InvokeStarted -> UiLoading(false)
                        is InvokeError -> UiError(it.throwable)
                        else -> UiIdle
                    }
                }
                .collect { messages.value = it }
        }
    }

    fun refresh() = refresh(true)

    protected fun refresh(fromUser: Boolean, fullRefresh: Boolean = true) {
        viewModelScope.launch {
            callRefresh(fromUser)
                .catch { messages.value = UiError(it) }
                .map {
                    when (it) {
                        InvokeSuccess -> UiSuccess
                        InvokeStarted -> UiLoading(fullRefresh)
                        else -> UiIdle
                    }
                }
                .collect { messages.value = it }
        }
    }

    protected abstract fun callRefresh(fromUser: Boolean): Flow<InvokeStatus>

    protected abstract fun callLoadMore(): Flow<InvokeStatus>
}
