package com.appetizercodingchallenge.common.paging

import com.appetizercodingchallenge.api.UiStatus

interface PagingViewState {
    val status: UiStatus
    val isLoaded: Boolean
}
