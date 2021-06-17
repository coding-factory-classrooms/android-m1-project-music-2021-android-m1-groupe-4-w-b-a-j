package com.trap.project_music.ui.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.trap.project_music.databinding.PlaylistFragmentBinding
import com.trap.project_music.model.PlaylistModel
import com.trap.project_music.ui.main.home.adapter.PlaylistAdapter
import com.trap.project_music.ui.main.home.viewmodel.PlayerViewModelState
import com.trap.project_music.ui.main.home.viewmodel.PlaylistViewModel
import com.trap.project_music.ui.main.home.viewmodel.PlaylistViewModelState

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
        model.getState().observe(viewLifecycleOwner, { state -> feedBackPlaylistCreator(state!!)})

        adapter = PlaylistAdapter(listOf())

        binding.recyclerPlaylist.adapter = adapter
        binding.recyclerPlaylist.layoutManager = LinearLayoutManager(context)

        model.loadPlaylists()

        binding.createPlaylistButton.setOnClickListener {
            model.createPlaylist(binding.editTextPlaylist.text.toString())
        }

    }

    private fun updatePlaylists(playlistModels: List<PlaylistModel>) {
        adapter.updateDataSet(playlistModels)
    }
    private fun feedBackPlaylistCreator(state: PlaylistViewModelState) {
        when (state) {
            PlaylistViewModelState.Success -> {
                Toast.makeText(context, "Playlist created", Toast.LENGTH_SHORT).show()
            }
            is PlaylistViewModelState.Failure -> {
                Toast.makeText(context, state.errorMessage, Toast.LENGTH_LONG).show()
            }

        }
    }


}