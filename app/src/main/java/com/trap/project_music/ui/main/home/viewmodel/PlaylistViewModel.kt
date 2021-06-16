package com.trap.project_music.ui.main.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trap.project_music.model.Playlist

private val playlists = listOf(
    Playlist(1, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistX"),
    Playlist(2, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistX"),
    Playlist(3, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistX"),
    Playlist(4, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistX"),

    Playlist(5, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistY"),
    Playlist(6, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistY"),
    Playlist(7, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistY"),
    Playlist(8, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistY"),
    Playlist(9, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistZ"),
    Playlist(10, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistZ"),
    Playlist(11, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistZ"),
    Playlist(12, "https://www.lovethispic.com/uploaded_images/thumbs/32559-Beautiful-Ocean.jpg", "playlistZ"),
)

class PlaylistViewModel : ViewModel() {
    private val playlistsLiveData = MutableLiveData<List<Playlist>>()
    fun getPlaylistsLiveData(): LiveData<List<Playlist>> = playlistsLiveData

    fun loadPlaylists() {
        playlistsLiveData.value = playlists
    }
}