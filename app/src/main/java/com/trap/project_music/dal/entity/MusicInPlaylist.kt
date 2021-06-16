package com.trap.project_music.dal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "musicInPlaylist_table")
class MusicInPlaylist (
    @PrimaryKey @ColumnInfo(name = "musicId") val myId: Int,
    @PrimaryKey @ColumnInfo(name = "playlistId") val playlistId: Int
)