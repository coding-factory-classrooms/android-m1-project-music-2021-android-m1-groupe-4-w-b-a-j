package com.trap.project_music.ui.main.home.viewmodel

import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trap.project_music.server.service.APISong
import com.trap.project_music.vo.SongJSON
import kotlinx.android.synthetic.main.player_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


sealed class PlayerViewModelState() {

    data class Success(val listSong: List<SongJSON>) : PlayerViewModelState()
    data class Loading(val message: String) : PlayerViewModelState()
    data class Failure(val errorMessage: String) : PlayerViewModelState()

    data class ChangeSong(val actualSong: SongJSON) : PlayerViewModelState()

}

class PlayerViewModel(private val apiSong: APISong) : ViewModel() {

    private val state = MutableLiveData<PlayerViewModelState>()

    private var listSong = mutableListOf<SongJSON>()

    private var actualSongIndex: Int = 0
    private val stateListSong = MutableLiveData<PlayerViewModelState>()

    private val actualSong = MutableLiveData<PlayerViewModelState>()

    fun getState(): LiveData<PlayerViewModelState> = state
    fun getStateListSong(): LiveData<PlayerViewModelState> = stateListSong
    fun getStateActualSong(): LiveData<PlayerViewModelState> = actualSong


    fun nextSong() {
        if (actualSongIndex < listSong.size) {
            actualSongIndex += 1
            changeActualSong(actualSongIndex)
        }
    }

    fun prevSong() {
        if (actualSongIndex > 0) {
            actualSongIndex -= 1
            changeActualSong(actualSongIndex)
        }
    }


    fun changeActualSong(index: Int) {
        actualSong.value = PlayerViewModelState.ChangeSong(listSong[index])
    }


    fun getSongs(artistId : Long) {
        stateListSong.value = PlayerViewModelState.Loading("Chargement : ")
        val serviceRequest = apiSong.getSongsByArtistID(artistId)
        serviceRequest.enqueue(object : Callback<List<SongJSON>> {

            override fun onFailure(call: Call<List<SongJSON>>, t: Throwable) {
                Log.v("test", "FAILURE $t")
                stateListSong.value = PlayerViewModelState.Failure("Error")
            }

            override fun onResponse(
                call: Call<List<SongJSON>>,
                response: Response<List<SongJSON>>
            ) {
                response.body()?.also { it ->
                    Log.d("RESPONSE", "SONG list $it")
                    listSong.addAll(it)
                    if (listSong.size > 0) {
                        changeActualSong(actualSongIndex)
                        stateListSong.value = PlayerViewModelState.Success(listSong)
                    } else {
                        stateListSong.value = PlayerViewModelState.Failure("Empty list")
                    }
                } ?: run {
                    stateListSong.value = PlayerViewModelState.Failure("list null")
                }
            }
        })

    }

}