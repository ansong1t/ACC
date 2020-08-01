package com.appetizercodingchallenge.data

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.appetizercodingchallenge.data.daos.ItemDao
import com.appetizercodingchallenge.data.daos.SongDao
import com.appetizercodingchallenge.data.daos.TvShowDao
import org.koin.dsl.module

val dbModules = module {
    single { provideDatabase(get()) }
    factory { provideItemDao(get()) }
    factory { provideTvShowDao(get()) }
    factory { provideSongDao(get()) }
}

@Suppress("SpreadOperator")
private fun provideDatabase(
    context: Context
): AccRoomDatabase {
    val builder = Room.databaseBuilder(
        context, AccRoomDatabase::class.java,
        "acc.db"
    ).addMigrations(*AccRoomDatabase_Migrations.build()) // autogenerated code by Roomigrant
        .fallbackToDestructiveMigration()
    if (Debug.isDebuggerConnected()) {
        builder.allowMainThreadQueries()
    }
    return builder.build()
}

private fun provideItemDao(db: AccRoomDatabase): ItemDao = db.itemDao()
private fun provideTvShowDao(db: AccRoomDatabase): TvShowDao = db.tvShowDao()
private fun provideSongDao(db: AccRoomDatabase): SongDao = db.songDao()
