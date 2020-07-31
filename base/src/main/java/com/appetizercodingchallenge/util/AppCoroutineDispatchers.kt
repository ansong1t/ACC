package com.appetizercodingchallenge.util

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class AppCoroutineDispatchers {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val io: CoroutineContext by lazy { Dispatchers.IO }
    open val default: CoroutineContext by lazy { Dispatchers.Default }
}
