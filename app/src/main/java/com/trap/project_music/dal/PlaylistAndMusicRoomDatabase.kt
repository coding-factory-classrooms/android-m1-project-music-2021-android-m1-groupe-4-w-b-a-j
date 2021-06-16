package com.trap.project_music.dal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.trap.project_music.dal.dao.MusicInPlaylistDAO
import com.trap.project_music.dal.dao.PlaylistDAO
import com.trap.project_music.dal.entity.Playlist

@Database(entities = arrayOf(Playlist::class), version = 1, exportSchema = false)
public abstract class PlaylistAndMusicRoomDatabase : RoomDatabase() {

    abstract fun playlistDao(): PlaylistDAO
    abstract fun musicInPlaylistDao() : MusicInPlaylistDAO
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PlaylistAndMusicRoomDatabase? = null

        fun getDatabase(context: Context): PlaylistAndMusicRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaylistAndMusicRoomDatabase::class.java,
                    "Playlist_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}