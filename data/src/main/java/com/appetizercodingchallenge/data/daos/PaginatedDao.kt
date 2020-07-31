package com.appetizercodingchallenge.data.daos

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.appetizercodingchallenge.data.entities.AccEntity

abstract class PaginatedDao<Entity : AccEntity> : EntityDao<Entity>() {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override suspend fun insert(entity: Entity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override suspend fun insertAll(vararg entity: Entity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override suspend fun insertAll(entities: List<Entity>)

    abstract suspend fun deletePage(page: Int)

    abstract suspend fun getLastPage(): Int?

    @Transaction
    open suspend fun updatePage(page: Int, entities: List<Entity>) {
        deletePage(page)
        insertAll(entities)
    }
}
