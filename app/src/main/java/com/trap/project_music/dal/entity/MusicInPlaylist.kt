package com.trap.project_music.dal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "musicInPlaylist_table", primaryKeys = ["musicId","playlistId"])
data class MusicInPlaylist (
    @ColumnInfo(name = "musicId") val musicId: Int,
    @ColumnInfo(name = "playlistId") val playlistId: Int
)
