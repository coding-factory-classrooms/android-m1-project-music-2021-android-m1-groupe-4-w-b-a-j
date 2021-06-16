package com.trap.project_music

import android.app.Application
import androidx.room.Room
import com.trap.project_music.dal.PlaylistAndMusicRoomDatabase

class PlaylistAndMusicApplication : Application() {

    companion object{
        lateinit var database: PlaylistAndMusicRoomDatabase
    }

    override fun onCreate() {
        super.onCreate()
        PlaylistAndMusicApplication.database = Room.databaseBuilder(
            this,
            PlaylistAndMusicRoomDatabase::class.java,
            "Playlist_database"
        ).allowMainThreadQueries()
            .build()
    }
}
