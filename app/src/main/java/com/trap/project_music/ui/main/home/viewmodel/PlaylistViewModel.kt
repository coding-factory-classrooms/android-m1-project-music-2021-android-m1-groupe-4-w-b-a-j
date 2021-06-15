package com.trap.project_music.ui.main.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trap.project_music.model.Playlist

private val playlists = listOf(
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistX"),
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistX"),
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistX"),
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistX"),

    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistY"),
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistY"),
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistY"),
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistY"),
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistZ"),
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistZ"),
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistZ"),
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistZ"),
)

class PlaylistViewModel : ViewModel() {
    private val playlistsLiveData = MutableLiveData<List<Playlist>>()
    fun getPlaylistsLiveData(): LiveData<List<Playlist>> = playlistsLiveData

    fun loadPlaylists() {
        playlistsLiveData.value = playlists
    }
}