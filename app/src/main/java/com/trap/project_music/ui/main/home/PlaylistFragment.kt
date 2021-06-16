package com.trap.project_music.ui.main.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.trap.project_music.R
import com.trap.project_music.databinding.PlaylistFragmentBinding
import com.trap.project_music.model.Playlist
import com.trap.project_music.ui.main.home.adapter.PlaylistAdapter
import com.trap.project_music.ui.main.home.viewmodel.PlaylistViewModel

class PlaylistFragment : Fragment() {

    private lateinit var adapter: PlaylistAdapter
    private val model: PlaylistViewModel by viewModels()
    private lateinit var binding: PlaylistFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlaylistFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getPlaylistsLiveData().observe(viewLifecycleOwner, { playlists -> updatePlaylists(playlists!!)})

        adapter = PlaylistAdapter(listOf())

        binding.recyclerPlaylist.adapter = adapter
        binding.recyclerPlaylist.layoutManager = LinearLayoutManager(context)

        model.loadPlaylists()

        //viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        // TODO: Use the ViewModel

        Log.d("test", "PLAYLIST FRAGMENT")
    }

    private fun updatePlaylists(playlists: List<Playlist>) {
        adapter.updateDataSet(playlists)
    }


}