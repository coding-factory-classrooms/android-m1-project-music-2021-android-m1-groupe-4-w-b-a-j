package com.trap.project_music.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trap.project_music.server.service.APISong
import com.trap.project_music.ui.main.home.viewmodel.PlayerViewModel

@Suppress("UNCHECKED_CAST")
class PlayerViewModelFactory(private val apiSong: APISong) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayerViewModel(apiSong) as T
    }
}


