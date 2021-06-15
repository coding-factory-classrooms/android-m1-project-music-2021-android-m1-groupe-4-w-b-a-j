package com.trap.project_music.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.trap.project_music.R
import com.trap.project_music.factory.ProfileViewModelFactory
import com.trap.project_music.model.Account
import com.trap.project_music.server.RetrofitFactory
import com.trap.project_music.server.service.APIArtist
import com.trap.project_music.ui.main.home.adapter.PostProfileAdapter
import com.trap.project_music.ui.main.home.listener.OnArtistClickListener
import com.trap.project_music.vo.ArtistJSON
import kotlinx.android.synthetic.main.profile_fragment.*


class ProfileFragment: Fragment() {
    private var listPost = mutableListOf<ArtistJSON>()
    private lateinit var viewModel: ProfileViewModel

    private lateinit var adapter: PostProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val onArtistClickListener: OnArtistClickListener = object : OnArtistClickListener {
            override fun invoke(artistId: Long) {
                Log.d("test", "ArtistId : $artistId")
            }

        }
        adapter = PostProfileAdapter(listPost,onArtistClickListener)
        recyclerPlaylist.adapter = adapter
        recyclerPlaylist.layoutManager = GridLayoutManager(context, 2)

        viewModel = ViewModelProvider(this, ProfileViewModelFactory(
            RetrofitFactory(requireContext()).createService(
            APIArtist::class.java))
        ).get(ProfileViewModel::class.java)

        viewModel.getStateListArtists().observe(viewLifecycleOwner, Observer { updateUI(it) })


        viewModel.account().observe(viewLifecycleOwner, Observer { updatePostInfo(it) })
    }


    private fun updatePostInfo(account: Account){
        display_name.text = account.name
        name.text = "@${account.name}"
  /*      profile_picture.load(account.profilePictureData.uri["medium"]) {
            transformations(CircleCropTransformation())
        }*/
    }

    private fun updateUI(state: ProfileViewModelState) {
        when (state) {
            is ProfileViewModelState.Succes -> {
                this.listPost.addAll(state.listArtistJSON)
                adapter.notifyDataSetChanged()
            }
            is ProfileViewModelState.Failure -> {
                println("Ca beug ProfileViewModelState")

            }
            is ProfileViewModelState.Loading -> {
                println("Loading")
            }
        }
    }

}
