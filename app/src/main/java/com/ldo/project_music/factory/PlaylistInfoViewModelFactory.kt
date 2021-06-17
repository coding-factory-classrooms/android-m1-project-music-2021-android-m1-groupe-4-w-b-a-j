package com.ldo.project_music.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ldo.project_music.MusicDownloader
import com.ldo.project_music.SongStorageSystem
import com.ldo.project_music.api.service.APIArtist
import com.ldo.project_music.api.service.APISong
import com.ldo.project_music.dal.dao.PlaylistDAO
import com.ldo.project_music.dal.dao.SongDAO
import com.ldo.project_music.ui.main.player.viewmodel.PlayerViewModel
import com.ldo.project_music.ui.main.playlist.viewmodel.PlaylistViewModel
import com.ldo.project_music.ui.main.playlistinfo.viewmodel.PlaylistInfoViewModel
import com.ldo.project_music.ui.main.profile.viewmodel.ProfileViewModel

@Suppress("UNCHECKED_CAST")
class PlaylistInfoViewModelFactory(private val playlistDAO: PlaylistDAO, private val songDAO: SongDAO,private val songStorageSystem: SongStorageSystem, private val musicDownloader: MusicDownloader) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaylistInfoViewModel(playlistDAO,songDAO,songStorageSystem,musicDownloader) as T
    }
}