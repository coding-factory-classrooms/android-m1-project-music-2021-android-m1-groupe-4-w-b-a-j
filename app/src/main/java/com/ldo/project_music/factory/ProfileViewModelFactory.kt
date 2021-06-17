package com.ldo.project_music.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldo.project_music.api.service.APIArtist
import com.ldo.project_music.api.service.APISong
import com.ldo.project_music.dal.dao.PlaylistDAO
import com.ldo.project_music.dal.dao.SongDAO
import com.ldo.project_music.ui.main.profile.viewmodel.ProfileViewModel

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val apiSong: APISong,private val apiArtist: APIArtist, private val playlistDAO: PlaylistDAO, private val songDAO: SongDAO) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(apiSong,apiArtist,playlistDAO,songDAO) as T
    }
}