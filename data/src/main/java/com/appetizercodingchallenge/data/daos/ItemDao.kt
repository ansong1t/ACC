package com.appetizercodingchallenge.data.daos

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.appetizercodingchallenge.data.entities.FeatureMovie
import com.appetizercodingchallenge.data.entities.Song
import com.appetizercodingchallenge.data.entities.ItemEntry
import com.appetizercodingchallenge.data.entities.SearchedItemEntry
import com.appetizercodingchallenge.data.entities.TvEpisode
import com.appetizercodingchallenge.data.entities.TvShow
import com.appetizercodingchallenge.data.mappers.ItemResponseToFeatureMovieMapper
import com.appetizercodingchallenge.data.mappers.ItemResponseToSongMapper
import com.appetizercodingchallenge.data.mappers.ItemResponseToTvEpisodeMapper
import com.appetizercodingchallenge.data.mappers.ItemResponseToTvShowMapper
import com.appetizercodingchallenge.data.responses.ItemResponse
import com.appetizercodingchallenge.data.resultentities.ItemEntryWithDetails
import com.appetizercodingchallenge.data.resultentities.SearchedItemEntryWithDetails
import com.appetizercodingchallenge.data.types.ListItemType
import com.appetizercodingchallenge.data.types.responseToTrackType

@Dao
abstract class ItemDao : EntityDao<Song>() {

    @Transaction
    @Query("SELECT * FROM item_entries")
    abstract fun getPagedItems(): DataSource.Factory<Int, ItemEntryWithDetails>

    @Transaction
    @Query("SELECT * FROM searched_item_entries")
    abstract fun getPagedSearchedItems(): DataSource.Factory<Int, SearchedItemEntryWithDetails>

    @Query("DELETE FROM searched_item_entries")
    abstract suspend fun clearSearchResults()

    @Query("DELETE FROM item_entries")
    abstract suspend fun clearItemEntries()

    @Query("SELECT EXISTS (SELECT 1 FROM item_entries LIMIT 1)")
    abstract suspend fun hasItemEntry(): Boolean

    suspend fun insertItems(
        items: List<ItemResponse>,
        songMapper: ItemResponseToSongMapper,
        featureMovieMapper: ItemResponseToFeatureMovieMapper,
        tvEpisodeMapper: ItemResponseToTvEpisodeMapper,
        tvShowMapper: ItemResponseToTvShowMapper
    ) {
        val tvEpisodes = arrayListOf<TvEpisode>()
        items.forEach {
            when (responseToTrackType(it.kind)) {
                ListItemType.SONG -> insertSong(songMapper(it))
                ListItemType.FEATURE_MOVIE -> insertFeatureMovie(featureMovieMapper(it))
                ListItemType.TV_SHOW -> {
                    insertTvShow(tvShowMapper(it))
                    tvEpisodes.add(tvEpisodeMapper(it))
                }
                else -> {
                    // TODO save audio book
                }
            }
            val entry = ItemEntry(
                trackId = if (responseToTrackType(it.kind) != ListItemType.TV_SHOW) it.trackId else it.collectionId,
                kind = responseToTrackType(it.kind)
            )
            insertItemEntry(entry)
        }
        insertTvEpisodes(tvEpisodes)
    }

    @Insert(entity = SearchedItemEntry::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSearchedItemEntry(entry: SearchedItemEntry)

    @Insert(entity = ItemEntry::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertItemEntry(entry: ItemEntry)

    @Insert(entity = Song::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSong(song: Song): Long

    @Insert(entity = FeatureMovie::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertFeatureMovie(featureMovie: FeatureMovie): Long

    @Insert(entity = TvEpisode::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTvEpisodes(tvEpisodes: List<TvEpisode>)

    @Insert(entity = TvShow::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTvShow(show: TvShow): Long
}
