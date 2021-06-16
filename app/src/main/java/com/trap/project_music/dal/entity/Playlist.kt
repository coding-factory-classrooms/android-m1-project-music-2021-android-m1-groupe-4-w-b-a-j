package com.trap.project_music.dal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist_table")
data class Playlist (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "playlistName") val playlistName: String,
    @ColumnInfo(name = "imageId") val imageId: Int,
)
