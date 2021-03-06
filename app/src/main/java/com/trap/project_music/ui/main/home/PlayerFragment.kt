package com.trap.project_music.ui.main.home

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.trap.project_music.R
import com.trap.project_music.factory.PlayerViewModelFactory
import com.trap.project_music.server.RetrofitFactory
import com.trap.project_music.server.service.APISong
import com.trap.project_music.ui.main.home.viewmodel.PlayerViewModel
import com.trap.project_music.ui.main.home.viewmodel.PlayerViewModelState
import com.trap.project_music.vo.SongJSON
import kotlinx.android.synthetic.main.player_fragment.*
import kotlinx.coroutines.Runnable

class PlayerFragment : Fragment() {

    private lateinit var viewModel: PlayerViewModel

    private var artistId: Long = 0
    private var listSong = mutableListOf<SongJSON>()

    private lateinit var actualSong: SongJSON


    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        arguments.let {
            Log.d("test", "onCreateView")
            it?.getLong("artistId")?.let {
                artistId = it
            }
        }

        return inflater.inflate(R.layout.player_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mediaPlayer = MediaPlayer()

        viewModel = ViewModelProvider(
            this,
            PlayerViewModelFactory(RetrofitFactory(requireContext()).createService(APISong::class.java))
        ).get(PlayerViewModel::class.java)

        viewModel.getStateListSong().observe(viewLifecycleOwner, Observer { updateUI(it) })

        viewModel.getStateActualSong()
            .observe(viewLifecycleOwner, Observer { updateActualSong(it) })

        viewModel.getSongs(artistId)



        play.setOnClickListener {
            this.stateMusic()

        }

        previous.setOnClickListener {
            viewModel.prevSong()
        }
        next.setOnClickListener {
            viewModel.nextSong()
        }



        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    override fun onPause() {
        super.onPause()
        if (this::runnable.isInitialized) handler.removeCallbacks(runnable)
        if (this::mediaPlayer.isInitialized) {
            try {
                mediaPlayer.reset()
                mediaPlayer.prepare()
                mediaPlayer.stop()
                mediaPlayer.release()

            } catch (e: Exception) {
                Log.d("test", "Erreur mediaplayer : $e")
            }
        }
    }

    private fun updateActualSong(state: PlayerViewModelState?) {
        when (state) {
            is PlayerViewModelState.ChangeSong -> {
                actualSong = state.actualSong
                changeSong()
                stateMusic()
            }
            is PlayerViewModelState.Failure -> {
                Toast.makeText(this.context, state.errorMessage, Toast.LENGTH_SHORT).show()
            }
            is PlayerViewModelState.Loading -> {
                Toast.makeText(this.context, state.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun updateUI(state: PlayerViewModelState?) {
        when (state) {
            is PlayerViewModelState.Success -> {
                this.listSong.addAll(state.listSong)
            }
            is PlayerViewModelState.Failure -> {
                Toast.makeText(this.context, state.errorMessage, Toast.LENGTH_SHORT).show()
            }
            is PlayerViewModelState.Loading -> {
                Toast.makeText(this.context, state.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun changeSong() {
        if (this::runnable.isInitialized) handler.removeCallbacks(runnable)
        songName.text = actualSong.name
        mediaPlayer.reset()
        mediaPlayer.setDataSource(actualSong.file)
        mediaPlayer.prepare()
        this.initializeSeekBar()
        handler.postDelayed(runnable, 1000)
    }


    private fun initializeSeekBar() {
        seekBar.max = mediaPlayer.duration
        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
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
