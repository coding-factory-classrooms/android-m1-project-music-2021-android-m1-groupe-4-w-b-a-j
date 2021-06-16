package com.trap.project_music.ui.main.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trap.project_music.R
import com.trap.project_music.model.PlaylistModel

private val playlists = listOf(
    PlaylistModel(1, R.drawable._667_photo_min, "playlistX"),
    PlaylistModel(2, R.drawable._667_photo_min, "playlistX"),
    PlaylistModel(3, R.drawable._667_photo_min, "playlistX"),
    PlaylistModel(4, R.drawable._667_photo_min, "playlistX"),

    PlaylistModel(5, R.drawable._667_photo_min, "playlistY"),
    PlaylistModel(6, R.drawable._667_photo_min, "playlistY"),
    PlaylistModel(7, R.drawable._667_photo_min, "playlistY"),
    PlaylistModel(8, R.drawable._667_photo_min, "playlistY"),
    PlaylistModel(9, R.drawable._667_photo_min, "playlistZ"),
    PlaylistModel(10, R.drawable._667_photo_min, "playlistZ"),
    PlaylistModel(11, R.drawable._667_photo_min, "playlistZ"),
    PlaylistModel(12, R.drawable._667_photo_min, "playlistZ"),
)

class PlaylistViewModel : ViewModel() {
    private val playlistsLiveData = MutableLiveData<List<PlaylistModel>>()
    fun getPlaylistsLiveData(): LiveData<List<PlaylistModel>> = playlistsLiveData

    fun loadPlaylists() {
        playlistsLiveData.value = playlists
    }
}