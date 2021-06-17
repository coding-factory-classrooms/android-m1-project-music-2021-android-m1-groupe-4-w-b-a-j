package com.ldo.project_music.dal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ldo.project_music.dal.dao.PlaylistDAO
import com.ldo.project_music.dal.dao.SongDAO
import com.ldo.project_music.dal.dao.SongStatDAO
import com.ldo.project_music.dal.entity.Playlist
import com.ldo.project_music.dal.entity.SongEntity
import com.ldo.project_music.dal.entity.SongStatEntity

const val DB_NAME = "lofify.db"
@Database(entities = [Playlist::class, SongEntity::class,SongStatEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDAO(): PlaylistDAO
    abstract fun songDAO(): SongDAO
    abstract fun songStateDAO(): SongStatDAO
}