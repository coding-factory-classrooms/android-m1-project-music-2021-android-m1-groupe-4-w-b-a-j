package com.trap.project_music.ui.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trap.project_music.PlaylistAndMusicApplication
import com.trap.project_music.R
import com.trap.project_music.dal.entity.Playlist
import com.trap.project_music.model.PlaylistModel

sealed class PlaylistViewModelState(
    open val errorMessage: String = "Invalid playlist name"
) {
    object Success : PlaylistViewModelState()
    data class Failure(override val errorMessage: String) :
        PlaylistViewModelState(errorMessage = errorMessage)
}

//private val playlists = PlaylistAndMusicApplication.database.playlistDao().getPlaylists()

class PlaylistViewModel() : ViewModel() {
    private val state = MutableLiveData<PlaylistViewModelState>()
    private val playlistsLiveData = MutableLiveData<List<PlaylistModel>>()
    fun getPlaylistsLiveData(): LiveData<List<PlaylistModel>> = playlistsLiveData
    fun getState() : LiveData<PlaylistViewModelState> = state

    fun loadPlaylists() {
        playlistsLiveData.value = convertDataToModels(PlaylistAndMusicApplication.database.playlistDao().getPlaylists())
    }

    private fun convertDataToModels(playlists: List<Playlist>): List<PlaylistModel> {
        val list: MutableList<PlaylistModel> = ArrayList()

        for (playList in playlists) {
            list.add(PlaylistModel(0, playList.imageId, playList.playlistName))
        }
        return list
    }

    public fun createPlaylist(playlistName: String) {
        if (playlistName != "") {
            PlaylistAndMusicApplication.database.playlistDao().insert(Playlist(0, playlistName, R.drawable._667_photo_min))
            loadPlaylists()
            state.value = PlaylistViewModelState.Success
            Log.i("playlist","Enrg : "+  PlaylistAndMusicApplication.database.playlistDao().getPlaylists().size)
        } else {
            state.value = PlaylistViewModelState.Failure("Invalid name")
        }
    }
}