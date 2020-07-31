package com.appetizercodingchallenge.data

import com.appetizercodingchallenge.data.daos.ItemDao

interface AccDatabase {
    fun itemDao(): ItemDao
}
