package com.ldo.project_music.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldo.project_music.dal.dao.PlaylistDAO
import com.ldo.project_music.dal.dao.SongDAO
import com.ldo.project_music.ui.main.playlist.viewmodel.PlaylistViewModel

@Suppress("UNCHECKED_CAST")
class PlaylistViewModelFactory(private val playlistDAO: PlaylistDAO, private val songDAO: SongDAO) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaylistViewModel(playlistDAO,songDAO) as T
    }
}