package com.trap.project_music.ui.main.home

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.lifecycle.Observer
import com.trap.project_music.ui.main.home.viewmodel.PlayerViewModel
import com.trap.project_music.R
import com.trap.project_music.factory.HomeViewModelFactory
import com.trap.project_music.factory.PlayerViewModelFactory
import com.trap.project_music.server.RetrofitFactory
import com.trap.project_music.server.service.APIArtist
import com.trap.project_music.server.service.APISong
import com.trap.project_music.ui.main.home.viewmodel.HomeViewModel
import com.trap.project_music.ui.main.home.viewmodel.HomeViewModelState
import com.trap.project_music.ui.main.home.viewmodel.PlayerViewModelState
import com.trap.project_music.vo.ArtistJSON
import com.trap.project_music.vo.SongJSON
import kotlinx.android.synthetic.main.player_fragment.*
import kotlinx.coroutines.Runnable
import java.lang.Exception

class PlayerFragment : Fragment() {

    private lateinit var viewModel: PlayerViewModel

    private var listSong = mutableListOf<SongJSON>()

    private lateinit var actualSong : SongJSON
    private var actualSongIndex : Int = 0

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.player_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mediaPlayer = MediaPlayer()

        viewModel = ViewModelProvider(this, PlayerViewModelFactory(RetrofitFactory(requireContext()).createService(APISong::class.java))).get(PlayerViewModel::class.java)

        viewModel.getStateListSong().observe(viewLifecycleOwner, Observer { updateUI(it) })
        viewModel.getSongs()



        play.setOnClickListener {
            this.stateMusic()
        }

        previous.setOnClickListener {
            if (actualSongIndex > 0) {
                actualSongIndex--
                actualSong = listSong[actualSongIndex]
                initalizeMediaPlayer()
            }
        }
        next.setOnClickListener {
            if (actualSongIndex < listSong.size) {
                actualSongIndex++
                actualSong = listSong[actualSongIndex]
                songName.text = actualSong.name
                initalizeMediaPlayer()
                stateMusic()
            }
        }



        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    private fun updateUI(state: PlayerViewModelState?) {
        when (state) {
            is PlayerViewModelState.Succes -> {
                this.listSong.addAll(state.listSong)
                this.actualSong = this.listSong[0]
                songName.text = actualSong.name
                this.initalizeMediaPlayer()
            }
            is PlayerViewModelState.Failure -> {
                println("Ca beug PlayerViewModelState")
            }
            is PlayerViewModelState.Loading -> {
                println("Loading")
            }
        }

    }

    private fun initalizeMediaPlayer(){
        mediaPlayer.reset()
        mediaPlayer.setDataSource(actualSong.file)
        mediaPlayer.prepare()
        this.initializeSeekBar()
    }

    private fun initializeSeekBar() {
        seekBar.max = mediaPlayer.duration
        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
           // handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    private fun stateMusic() {
        var text = ""
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            text = "Audio stopped...."
        } else {
            mediaPlayer.start()
            text = "Audio playing...."
        }
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }



}
