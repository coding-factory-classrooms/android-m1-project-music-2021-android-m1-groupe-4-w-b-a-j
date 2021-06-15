package com.trap.project_music.ui.main.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.trap.project_music.R
import com.trap.project_music.ui.main.home.adapter.PlaylistAdapter
import com.trap.project_music.ui.main.home.viewmodel.PlaylistViewModel

class PlaylistFragment : Fragment() {

    private lateinit var adapter: PlaylistAdapter
    private val model: PlaylistViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.playlist_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PlaylistAdapter(listOf())

        model.loadPlaylists()

        //viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        // TODO: Use the ViewModel

        Log.d("test", "PLAYLIST FRAGMENT")
    }

}