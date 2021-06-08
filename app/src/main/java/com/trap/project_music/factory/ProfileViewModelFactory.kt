package com.trap.project_music.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trap.project_music.server.service.APIPost
import com.trap.project_music.ui.main.ProfileViewModel

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val apiPost: APIPost) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(apiPost) as T
    }
}