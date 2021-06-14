package com.trap.project_music.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.trap.project_music.R
import com.trap.project_music.common.attachSnapHelperWithListener
import com.trap.project_music.enums.VoteType
import com.trap.project_music.factory.HomeViewModelFactory
import com.trap.project_music.server.RetrofitFactory
import com.trap.project_music.server.service.APIArtist
import com.trap.project_music.ui.main.home.adapter.PostAdapter
import com.trap.project_music.ui.main.home.listener.OnPostClickListener
import com.trap.project_music.ui.main.home.listener.OnSnapPositionChangeListener
import com.trap.project_music.ui.main.home.listener.SnapOnScrollListener
import com.trap.project_music.ui.main.home.viewmodel.HomeViewModel
import com.trap.project_music.ui.main.home.viewmodel.HomeViewModelState
import com.trap.project_music.vo.ArtistJSON
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {
    private var listPost = mutableListOf<ArtistJSON>()
    private lateinit var adapter: PostAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val onPostClickListener: OnPostClickListener = object : OnPostClickListener {
            override fun invoke(voteType: VoteType, idPost: Long) {
                //TODO implements Onclick
            }

        }

        adapter = PostAdapter(listPost,onPostClickListener)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        val snapHelper : SnapHelper = LinearSnapHelper()

        recycler.attachSnapHelperWithListener(snapHelper,SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,object : OnSnapPositionChangeListener{
            override fun onSnapPositionChange(position: Int) {
                viewModel.changeActualPost(position)
            }

        })

        viewModel = ViewModelProvider(this, HomeViewModelFactory(RetrofitFactory(requireContext()).createService(APIArtist::class.java))).get(HomeViewModel::class.java)
        viewModel.getStateListPost().observe(viewLifecycleOwner, Observer { updateUI(it) })

        viewModel.actualPost().observe(viewLifecycleOwner, Observer { updatePostInfo(it) })
        viewModel.getArtists()
    }

    private fun updatePostInfo(artist: ArtistJSON){
        return
    }

    private fun updateUI(state: HomeViewModelState) {
        when (state) {
            is HomeViewModelState.Succes -> {
                this.listPost.addAll(state.listArtistJSON)
                adapter.notifyDataSetChanged()
            }
            is HomeViewModelState.Failure -> {
                println("Ca beug HomeViewModelState")

            }
            is HomeViewModelState.Loading -> {
                println("Loading")
            }
        }
    }

}
