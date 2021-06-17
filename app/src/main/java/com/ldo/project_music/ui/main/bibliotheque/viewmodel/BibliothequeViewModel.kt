package com.ldo.project_music.ui.main.bibliotheque.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ldo.project_music.api.service.APIArtist
import com.ldo.project_music.model.Artist
import com.ldo.project_music.ui.main.bibliotheque.viewmodel.interfaces.IBibliothequeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


sealed class BibliothequetState {

    data class Success(val artistes : List<Artist>): BibliothequetState()
    data class Loading(val message : String): BibliothequetState()
    data class Failure(val errorMessage: String) : BibliothequetState()

}

class BibliothequeViewModel(private val apiArtist: APIArtist, application: Application) : AndroidViewModel(application), IBibliothequeViewModel {

    private var state: MutableLiveData<BibliothequetState> = MutableLiveData()

    override fun getState(): LiveData<BibliothequetState> = state

    override fun getArtists() {
        state.postValue(BibliothequetState.Loading("Loading"))

        apiArtist.getArtists().enqueue(object : Callback<List<Artist>> {
            override fun onFailure(call: Call<List<Artist>>, t: Throwable) {
                Log.d("test","error get artists")
                state.postValue(BibliothequetState.Failure("Error"))
            }

            override fun onResponse(call: Call<List<Artist>>, response: Response<List<Artist>>) {
                response.body()?.let { state.postValue(BibliothequetState.Success(it)) }
                    ?: run {  state.postValue(BibliothequetState.Failure("Error")) }
            }
        })
    }

}