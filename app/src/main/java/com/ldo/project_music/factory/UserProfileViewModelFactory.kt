package com.ldo.project_music.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldo.project_music.UserProfileViewModel
import com.ldo.project_music.api.service.APIArtist
import com.ldo.project_music.api.service.APISong
import com.ldo.project_music.dal.dao.SongStatDAO

@Suppress("UNCHECKED_CAST")
class UserProfileViewModelFactory(private val apiSong: APISong, private val apiArtist: APIArtist, private val application: Application, private val songStatDAO: SongStatDAO) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserProfileViewModel(apiSong,apiArtist,application,songStatDAO) as T
    }
}