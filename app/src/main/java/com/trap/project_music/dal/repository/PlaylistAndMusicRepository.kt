package com.trap.project_music.dal.repository

import androidx.annotation.WorkerThread
import com.trap.project_music.dal.dao.MusicInPlaylistDAO
import com.trap.project_music.dal.dao.PlaylistDAO
import com.trap.project_music.dal.entity.Playlist
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class PlaylistAndMusicRepository(private val playlistDao: PlaylistDAO, private val musicInPlaylistDao: MusicInPlaylistDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allPlaylist: Flow<List<Playlist>> = playlistDao.getPlaylists()
    //val allMusicInPlaylist: Flow<List<MusicInPlaylist>> = musicInPlaylistDao.getMusics()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(playlist: Playlist) {
        playlistDao.insert(playlist)
    }

    /*
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(musicInPlaylist: MusicInPlaylist) {
        musicInPlaylistDao.insert(musicInPlaylist)
    }
    */
}
