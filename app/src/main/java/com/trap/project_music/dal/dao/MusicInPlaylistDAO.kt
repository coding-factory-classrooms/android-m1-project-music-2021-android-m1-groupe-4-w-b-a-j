package com.trap.project_music.dal.dao
import androidx.room.*

import com.trap.project_music.dal.entity.Playlist
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicInPlaylistDAO {
/*
    @Query("SELECT * FROM musicInPlaylist_table")
    fun getMusics(): Flow<List<MusicInPlaylist>>

    @Query("SELECT * FROM musicInPlaylist_table WHERE playlistId = :id")
    fun getMusicsOfAPlaylist(id: Int): List<MusicInPlaylist>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(musicInPlaylist: MusicInPlaylist)

    @Query("DELETE FROM musicInPlaylist_table WHERE playlistId = :playlistId")
    suspend fun deleteByPlaylistId(playlistId: Int)

    @Query("DELETE FROM musicInPlaylist_table")
    suspend fun deleteAll()

    @Query("DELETE FROM musicInPlaylist_table WHERE playlistId = :playlistId AND musicId = :musicId")
    suspend fun deleteMusicInPlaylist(musicId:Int, playlistId: Int)

    @Delete
    fun delete(playlist: Playlist)
*/

}