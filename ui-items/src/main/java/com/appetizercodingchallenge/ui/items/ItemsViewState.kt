package com.appetizercodingchallenge.ui.items

import com.appetizercodingchallenge.api.UiIdle
import com.appetizercodingchallenge.api.UiStatus
import com.appetizercodingchallenge.common.paging.PagingViewState

data class ItemsViewState(
    val isEmpty: Boolean = false,
    override val status: UiStatus = UiIdle,
    override val isLoaded: Boolean = false
) : PagingViewState
