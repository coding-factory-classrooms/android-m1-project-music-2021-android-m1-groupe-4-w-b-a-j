package com.trap.project_music.dal.dao

import androidx.room.*
import com.trap.project_music.dal.entity.Playlist
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDAO {

    @Query("SELECT * FROM playlist_table ORDER BY playListName ASC")
    fun getPlaylists(): Flow<List<Playlist>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(playlist: Playlist)

    @Query("DELETE FROM playlist_table")
    suspend fun deleteAll()

    @Query("DELETE FROM playlist_table WHERE id = :id")
    fun delete(id: Int)


}