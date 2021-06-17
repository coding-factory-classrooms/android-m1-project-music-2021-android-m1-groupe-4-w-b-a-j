package com.ldo.project_music.ui.main.profile.viewmodel

import androidx.lifecycle.LiveData

interface IProfileViewModel {
    fun getArtistState() : LiveData<ArtistProfileState>
    fun getSongsState(): LiveData<SongsProfileState>
    fun getSongsFromArtistId(id:Long)
    fun getArtistById(id:Long)
}