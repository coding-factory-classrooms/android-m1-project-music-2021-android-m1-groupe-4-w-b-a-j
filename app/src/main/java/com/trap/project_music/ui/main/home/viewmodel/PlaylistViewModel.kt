package com.trap.project_music.ui.main.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trap.project_music.R
import com.trap.project_music.model.Playlist

private val playlists = listOf(
    Playlist(1, R.drawable._667_photo_min, "playlistX"),
    Playlist(2, R.drawable._667_photo_min, "playlistX"),
    Playlist(3, R.drawable._667_photo_min, "playlistX"),
    Playlist(4, R.drawable._667_photo_min, "playlistX"),

    Playlist(5, R.drawable._667_photo_min, "playlistY"),
    Playlist(6, R.drawable._667_photo_min, "playlistY"),
    Playlist(7, R.drawable._667_photo_min, "playlistY"),
    Playlist(8, R.drawable._667_photo_min, "playlistY"),
    Playlist(9, R.drawable._667_photo_min, "playlistZ"),
    Playlist(10, R.drawable._667_photo_min, "playlistZ"),
    Playlist(11, R.drawable._667_photo_min, "playlistZ"),
    Playlist(12, R.drawable._667_photo_min, "playlistZ"),
)

class PlaylistViewModel : ViewModel() {
    private val playlistsLiveData = MutableLiveData<List<Playlist>>()
    fun getPlaylistsLiveData(): LiveData<List<Playlist>> = playlistsLiveData

    fun loadPlaylists() {
        playlistsLiveData.value = playlists
    }
}