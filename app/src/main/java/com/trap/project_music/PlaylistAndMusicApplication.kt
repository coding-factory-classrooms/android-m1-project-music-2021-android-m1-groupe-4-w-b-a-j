package com.trap.project_music

import android.app.Application
import com.trap.project_music.dal.PlaylistAndMusicRoomDatabase
import com.trap.project_music.dal.repository.PlaylistAndMusicRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PlaylistAndMusicApplication : Application() {

    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { PlaylistAndMusicRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { PlaylistAndMusicRepository(database.playlistDao(),database.musicInPlaylistDao()) }
}
