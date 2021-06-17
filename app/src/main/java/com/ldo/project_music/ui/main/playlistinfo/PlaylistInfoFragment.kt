package com.ldo.project_music.ui.main.playlistinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ldo.project_music.MusicDownloader
import com.ldo.project_music.R
import com.ldo.project_music.SongStorageFactory
import com.ldo.project_music.api.RetrofitFactory
import com.ldo.project_music.api.service.APISong
import com.ldo.project_music.common.makeToast
import com.ldo.project_music.dal.DatabaseFactory
import com.ldo.project_music.factory.PlaylistInfoViewModelFactory
import com.ldo.project_music.model.Song
import com.ldo.project_music.ui.main.playlistinfo.viewmodel.DownloadState
import com.ldo.project_music.ui.main.playlistinfo.viewmodel.PlaylistInfoViewModel
import com.ldo.project_music.ui.main.playlistinfo.viewmodel.SongAndDownLoad
import com.ldo.project_music.ui.main.profile.viewmodel.adapter.OnSongClickListener
import com.ldo.project_music.ui.main.profile.viewmodel.adapter.SongsAdapter
import kotlinx.android.synthetic.main.playlist_info_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.btnReturn
import kotlinx.android.synthetic.main.profile_fragment.recyclerSongs

class PlaylistInfoFragment : Fragment() {

    private lateinit var viewModel: PlaylistInfoViewModel
    private var listSongs = mutableListOf<Song>()
    private lateinit var songsAdapter: SongsAdapter
    private var playlistId: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.playlist_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            PlaylistInfoViewModelFactory(
                DatabaseFactory.create(requireContext()).playlistDAO(),
                DatabaseFactory.create(requireContext()).songDAO(),
                SongStorageFactory(requireContext(),activity?.application!!).create(),
                MusicDownloader(RetrofitFactory(requireContext()).createService(APISong::class.java))
            )
        ).get(PlaylistInfoViewModel::class.java)


        val onSongClickListener: OnSongClickListener = object : OnSongClickListener {
            override fun invoke(song: Song) {
                val bundle: Bundle= Bundle()
                bundle.putParcelable("songId",song)
                bundle.putString("playlistId",playlistId.toString())
                findNavController().navigate(R.id.action_playlistInfoFragment_to_player,bundle)
            }

            override fun addPlaylist(song: Song) {

            }

        }

        songsAdapter = SongsAdapter(listSongs,onSongClickListener)

        recyclerSongs.adapter = songsAdapter;
        recyclerSongs.layoutManager  = LinearLayoutManager(this.context)

        viewModel.getStateListSong().observe(viewLifecycleOwner,{updateSongList(it)})
        viewModel.getDownloadingState().observe(viewLifecycleOwner,{
            when(it){
                is DownloadState.Added -> {
                    nbSongSave.text = it.songsAndDownLoad.filter { songAndDownLoad -> songAndDownLoad.isDownloaded }.size.toString()
                }
                is DownloadState.IsDownloading -> {
                    if(it.isDownloading) {
                        downloadBtn.text = "Cancel"
                    }else{
                        downloadBtn.text = "Download"
                    }

                }
                is DownloadState.Error -> {
                    makeToast(it.errorMessage)
                }
            }
        })

        arguments?.also {

            it.getLong("playlistId").let {
                viewModel.getSongsFromPlaylist(it)
                playlistId = it
            }
        }

        btnReturn.setOnClickListener{
            findNavController().popBackStack()
        }

        downloadBtn.setOnClickListener {
            viewModel.toggleDownload()
        }

    }
    private fun updateSongList(songsAndDownLoad :List<SongAndDownLoad>){
        listSongs.clear()
        listSongs.addAll(songsAndDownLoad.map { songAndDownLoad-> songAndDownLoad.song})
        songsAdapter.notifyDataSetChanged()
        nbSongs.text = songsAndDownLoad.size.toString()
        nbSongSave.text = songsAndDownLoad.filter { songAndDownLoad -> songAndDownLoad.isDownloaded }.size.toString()
    }

}