package com.trap.project_music.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trap.project_music.dal.repository.PlaylistAndMusicRepository
import com.trap.project_music.ui.main.home.viewmodel.PlaylistViewModel

class PlaylistViewModelFactory(private val repository: PlaylistAndMusicRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaylistViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlaylistViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}