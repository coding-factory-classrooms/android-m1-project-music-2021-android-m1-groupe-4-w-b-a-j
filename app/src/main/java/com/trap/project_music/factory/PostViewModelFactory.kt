package com.trap.project_music.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trap.project_music.server.service.APIPost
import com.trap.project_music.ui.sign.viewmodel.PostViewModel

class PostViewModelFactory(private val apiPost: APIPost) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(apiPost) as T
    }
}