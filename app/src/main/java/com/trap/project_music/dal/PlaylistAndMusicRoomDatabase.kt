package com.trap.project_music.dal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.trap.project_music.R
import com.trap.project_music.dal.dao.MusicInPlaylistDAO
import com.trap.project_music.dal.dao.PlaylistDAO
import com.trap.project_music.dal.entity.Playlist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Playlist::class), version = 1, exportSchema = false)
public abstract class PlaylistAndMusicRoomDatabase : RoomDatabase() {

    abstract fun playlistDao(): PlaylistDAO
    abstract fun musicInPlaylistDao(): MusicInPlaylistDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PlaylistAndMusicRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PlaylistAndMusicRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaylistAndMusicRoomDatabase::class.java,
                    "Playlist_database"
                )
                    .addCallback(PlaylistAndMusicDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class PlaylistAndMusicDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.playlistDao(),database.musicInPlaylistDao())
                }
            }
        }

        suspend fun populateDatabase(playlistDao: PlaylistDAO, musicAndPlaylistDao: MusicInPlaylistDAO) {
            // Delete all content here.
            playlistDao.deleteAll()
            //musicAndPlaylistDao.deleteAll()

            // Add sample playlist.
            var playlist = Playlist(1,"ma playlist",R.drawable._667_photo_min)
            playlistDao.insert(playlist)

            /*
            var musicInPlaylist = MusicInPlaylist(1,1)
            musicAndPlaylistDao.insert(musicInPlaylist)
            musicInPlaylist = MusicInPlaylist(2,1)
            musicAndPlaylistDao.insert(musicInPlaylist)
            */
        }
    }

}

