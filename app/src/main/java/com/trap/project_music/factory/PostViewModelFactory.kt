package com.trap.project_music.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trap.project_music.server.service.APIArtist
import com.trap.project_music.ui.sign.viewmodel.PostViewModel

class PostViewModelFactory(private val apiArtist: APIArtist) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(apiArtist) as T
    }
}