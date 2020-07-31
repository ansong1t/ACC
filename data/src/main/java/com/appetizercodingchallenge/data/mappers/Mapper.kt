package com.appetizercodingchallenge.data.mappers

interface Mapper<F, T> {
    suspend operator fun invoke(from: F): T
}

interface IndexedMapper<F, T> {
    suspend fun map(index: Int, from: F): T
}
