package com.trap.project_music.ui.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trap.project_music.server.service.APIArtist
import com.trap.project_music.server.service.APISong
import com.trap.project_music.vo.ArtistJSON
import com.trap.project_music.vo.SongJSON
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


sealed class PlayerViewModelState() {

    data class Succes(val listSong: List<SongJSON>) : PlayerViewModelState()
    data class Loading(val message: String) : PlayerViewModelState()
    data class Failure(val errorMessage: String) : PlayerViewModelState()

}

class PlayerViewModel(private val apiSong: APISong) : ViewModel() {


    private val state = MutableLiveData<PlayerViewModelState>()


    private lateinit var actualSong : SongJSON
    private  var actualSongIndex : Int = 0
    private val stateListSong = MutableLiveData<PlayerViewModelState>()

    fun getState(): LiveData<PlayerViewModelState> = state
    fun getStateListSong(): LiveData<PlayerViewModelState> = stateListSong



    fun nextSong(){
        if (actualSongIndex < 10){
        actualSongIndex++
        actualSong
        }
    }
    fun prevSong(){
        actualSong
    }

    fun getSongs() {
        stateListSong.value = PlayerViewModelState.Loading("Chargement : ")
        val serviceRequest = apiSong.getSongsByArtistID(2)
        serviceRequest.enqueue(object : Callback<List<SongJSON>> {

            override fun onFailure(call: Call<List<SongJSON>>, t: Throwable) {
                Log.v("test", "FAILURE $t")
                stateListSong.value = PlayerViewModelState.Failure("Error")
            }

            override fun onResponse(call: Call<List<SongJSON>>, response: Response<List<SongJSON>>) {
                response.body()?.also { it ->
                    Log.d("RESPONSE", "SONG list $it")

                    val listSong : List<SongJSON> = it
                    actualSong = listSong[actualSongIndex]

                    stateListSong.value = PlayerViewModelState.Succes(listSong)
                } ?: run {
                    stateListSong.value = PlayerViewModelState.Failure("list null")
                }
            }
        })

    }

}