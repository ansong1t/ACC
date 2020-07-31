package com.appetizercodingchallenge.ui.searchitems

import com.appetizercodingchallenge.api.UiIdle
import com.appetizercodingchallenge.api.UiStatus
import com.appetizercodingchallenge.common.paging.PagingViewState

data class SearchItemsViewState(
    val isEmpty: Boolean = false,
    override val status: UiStatus = UiIdle,
    override val isLoaded: Boolean = false
) : PagingViewState
