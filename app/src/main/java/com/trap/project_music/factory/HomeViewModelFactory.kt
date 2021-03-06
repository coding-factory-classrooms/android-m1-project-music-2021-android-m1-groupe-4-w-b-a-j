package com.trap.project_music.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trap.project_music.server.service.APIArtist
import com.trap.project_music.ui.main.home.viewmodel.HomeViewModel

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val apiArtist: APIArtist) : ViewModelProvider.NewInstanceFactory(){

   override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(apiArtist) as T
    }
}