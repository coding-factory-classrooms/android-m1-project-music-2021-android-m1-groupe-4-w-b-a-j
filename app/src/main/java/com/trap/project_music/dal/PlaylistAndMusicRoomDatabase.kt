package com.trap.project_music.dal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.trap.project_music.R
import com.trap.project_music.dal.dao.MusicInPlaylistDAO
import com.trap.project_music.dal.dao.PlaylistDAO
import com.trap.project_music.dal.entity.MusicInPlaylist
import com.trap.project_music.dal.entity.Playlist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Playlist::class, MusicInPlaylist::class), version = 1, exportSchema = false)
public abstract class PlaylistAndMusicRoomDatabase : RoomDatabase() {

    abstract fun playlistDao(): PlaylistDAO
    abstract fun musicInPlaylistDao(): MusicInPlaylistDAO
}

