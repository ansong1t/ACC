package com.appetizercodingchallenge.data.daos

import androidx.room.Dao
import androidx.room.Query
import com.appetizercodingchallenge.data.entities.Song
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SongDao : EntityDao<Song>() {

    @Query("SELECT * FROM songs WHERE track_id = :trackId")
    abstract fun getObservableSong(trackId: Long): Flow<Song>

    @Query("SELECT * FROM songs WHERE collection_id = :collectionId ORDER BY track_number")
    abstract fun getSongsByAlbum(collectionId: Long): Flow<List<Song>>

    @Query("SELECT * FROM songs WHERE artist_id = :artistId AND track_id != :currentTrackId")
    abstract fun getRelatedSongs(currentTrackId: Long, artistId: Long): Flow<List<Song>>
}
