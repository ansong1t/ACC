package com.appetizercodingchallenge.data

import com.appetizercodingchallenge.data.daos.AudioBookDao
import com.appetizercodingchallenge.data.daos.ItemDao
import com.appetizercodingchallenge.data.daos.MovieDao
import com.appetizercodingchallenge.data.daos.SongDao
import com.appetizercodingchallenge.data.daos.TvShowDao

interface AccDatabase {
    fun itemDao(): ItemDao
    fun tvShowDao(): TvShowDao
    fun songDao(): SongDao
    fun movieDao(): MovieDao
    fun audioBookDao(): AudioBookDao
}
