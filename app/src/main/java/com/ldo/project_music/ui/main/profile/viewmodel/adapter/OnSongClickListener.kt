package com.ldo.project_music.ui.main.profile.viewmodel.adapter

import com.ldo.project_music.model.Song

interface OnSongClickListener {
    fun invoke(song: Song)
    fun addPlaylist(song: Song)
}