package com.appetizercodingchallenge.data.repositories

import com.appetizercodingchallenge.data.daos.SongDao
import com.appetizercodingchallenge.data.entities.Song
import kotlinx.coroutines.flow.Flow

class SongRepositoryImpl(
    private val songDao: SongDao
) : SongRepository {

    override fun observeSong(trackId: Long): Flow<Song> = songDao.getObservableSong(trackId)

    override fun observeRelatedSongs(trackId: Long, artistId: Long): Flow<List<Song>> =
        songDao.getRelatedSongs(trackId, artistId)

    override fun observeSongsByAlbum(collectionId: Long): Flow<List<Song>> =
        songDao.getSongsByAlbum(collectionId)
}
