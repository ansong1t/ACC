package com.appetizercodingchallenge.data

import com.appetizercodingchallenge.data.daos.ItemDao
import com.appetizercodingchallenge.data.daos.TvShowDao

interface AccDatabase {
    fun itemDao(): ItemDao
    fun tvShowDao(): TvShowDao
}
