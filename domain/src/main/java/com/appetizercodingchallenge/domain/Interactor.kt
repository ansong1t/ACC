/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appetizercodingchallenge.domain

import androidx.paging.PagedList
import com.appetizercodingchallenge.base.InvokeError
import com.appetizercodingchallenge.base.InvokeStarted
import com.appetizercodingchallenge.base.InvokeStatus
import com.appetizercodingchallenge.base.InvokeSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit

abstract class Interactor<in P> {
    operator fun invoke(params: P, timeoutMs: Long = defaultTimeoutMs): Flow<InvokeStatus> {
        return flow {
            try {
                withTimeout(timeoutMs) {
                    emit(InvokeStarted)
                    try {
                        doWork(params)
                        emit(InvokeSuccess)
                    } catch (t: Throwable) {
                        emit(InvokeError(t))
                    }
                }
            } catch (t: TimeoutCancellationException) {
                emit(InvokeError(t))
            }
        }
    }

    suspend fun executeSync(params: P) = doWork(params)

    protected abstract suspend fun doWork(params: P)

    companion object {
        private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
    }
}

abstract class ResultInteractor<in P, R> {
    operator fun invoke(params: P): Flow<R> {
        return flow {
            emit(doWork(params))
        }
    }

    protected abstract suspend fun doWork(params: P): R
}

@ExperimentalCoroutinesApi
abstract class PagingInteractor<P : PagingInteractor.Parameters<T>, T> : SubjectInteractor<P, PagedList<T>>() {
    interface Parameters<T> {
        val pagingConfig: PagedList.Config
        val boundaryCallback: PagedList.BoundaryCallback<T>?
    }
}

@ExperimentalCoroutinesApi
abstract class SuspendingWorkInteractor<P : Any, T> : SubjectInteractor<P, T>() {
    override fun createObservable(params: P): Flow<T> = flow {
        emit(doWork(params))
    }

    abstract suspend fun doWork(params: P): T
}

@ExperimentalCoroutinesApi
abstract class SubjectInteractor<P : Any, T> {
    private val paramState = MutableStateFlow<P?>(null)

    operator fun invoke(params: P) {
        paramState.value = params
    }

    protected abstract fun createObservable(params: P): Flow<T>

    fun observe(): Flow<T> = paramState.filterNotNull().flatMapLatest { createObservable(it) }
}

operator fun Interactor<Unit>.invoke() = invoke(Unit)
@ExperimentalCoroutinesApi
operator fun <T> SubjectInteractor<Unit, T>.invoke() = invoke(Unit)
