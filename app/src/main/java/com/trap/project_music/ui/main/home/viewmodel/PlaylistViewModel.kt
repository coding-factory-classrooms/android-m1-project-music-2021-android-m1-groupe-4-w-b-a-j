package com.trap.project_music.ui.main.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trap.project_music.PlaylistAndMusicApplication
import com.trap.project_music.R
import com.trap.project_music.dal.entity.Playlist
import com.trap.project_music.model.PlaylistModel

private val playlists = PlaylistAndMusicApplication.database.playlistDao().getPlaylists()
//    listOf(
//    PlaylistModel(1, R.drawable._667_photo_min, "playlistX"),
//    PlaylistModel(2, R.drawable._667_photo_min, "playlistX"),
//    PlaylistModel(3, R.drawable._667_photo_min, "playlistX"),
//    PlaylistModel(4, R.drawable._667_photo_min, "playlistX"),
//
//    PlaylistModel(5, R.drawable._667_photo_min, "playlistY"),
//    PlaylistModel(6, R.drawable._667_photo_min, "playlistY"),
//    PlaylistModel(7, R.drawable._667_photo_min, "playlistY"),
//    PlaylistModel(8, R.drawable._667_photo_min, "playlistY"),
//    PlaylistModel(9, R.drawable._667_photo_min, "playlistZ"),
//    PlaylistModel(10, R.drawable._667_photo_min, "playlistZ"),
//    PlaylistModel(11, R.drawable._667_photo_min, "playlistZ"),
//    PlaylistModel(12, R.drawable._667_photo_min, "playlistZ"),
//)

class PlaylistViewModel() : ViewModel() {
    private val playlistsLiveData = MutableLiveData<List<PlaylistModel>>()
    fun getPlaylistsLiveData(): LiveData<List<PlaylistModel>> = playlistsLiveData

    fun loadPlaylists() {
        playlistsLiveData.value = convertDataInModel(playlists)
    }

    private fun convertDataInModel(playlists: List<Playlist>): List<PlaylistModel> {
        val list: MutableList<PlaylistModel> = ArrayList()

        for (playList in playlists) {
            list.add(PlaylistModel(0, playList.imageId, playList.playlistName))
        }
        return list
    }
    // Using LiveData and caching what allplaylists returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
//    val allPlaylist: LiveData<List<Playlist>> = repository.allPlaylist.asLiveData()
//
//    /**
//     * Launching a new coroutine to insert the data in a non-blocking way
//     */
//    fun insert(playlist: Playlist) = viewModelScope.launch {
//        repository.insert(playlist)
//    }
}