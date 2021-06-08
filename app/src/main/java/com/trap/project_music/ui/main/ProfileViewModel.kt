package com.trap.project_music.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trap.project_music.model.Account
import com.trap.project_music.server.service.APIPost
import com.trap.project_music.ui.main.home.viewmodel.HomeViewModelState
import com.trap.project_music.vo.PageJSON
import com.trap.project_music.vo.PostJSON
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log


sealed class ProfileViewModelState() {

    data class Succes(val listPostJSON: List<PostJSON>) : ProfileViewModelState()
    data class Loading(val message: String) : ProfileViewModelState()
    data class Failure(val errorMessage: String) : ProfileViewModelState()

}

const val TAG = "ProfileViewModel"

class ProfileViewModel(private val apiPost: APIPost) : ViewModel() {

    private val state = MutableLiveData<ProfileViewModelState>()


    private val account = MutableLiveData<Account>()
    fun account(): LiveData<Account> = account
    private var listPostJSON: ArrayList<PostJSON> = ArrayList()

    private val stateListPost = MutableLiveData<ProfileViewModelState>()
    fun getState(): LiveData<ProfileViewModelState> = state
    fun getStateListPost(): LiveData<ProfileViewModelState> = stateListPost


    fun getPosts(accountId : Long) {
        Log.d("test","execution de la requete")
        val serviceRequest = apiPost.getPostsByAccount(accountId)
        serviceRequest.enqueue(object : Callback<List<PostJSON>> {
            override fun onFailure(call: Call<List<PostJSON>>, t: Throwable) {
                Log.v("test", "FAILURE $t")
                Log.d("test","erreur execution de la requete $call")
                stateListPost.value = ProfileViewModelState.Failure("Error")
            }

            override fun onResponse(
                call: Call<List<PostJSON>>,
                response: Response<List<PostJSON>>
            ) {
                response.body()?.also { it ->
                    stateListPost.value = ProfileViewModelState.Succes(it)

                    listPostJSON = it as ArrayList<PostJSON>

                    // set actualpost to first post

                    if (listPostJSON.isNotEmpty()) {
                     Log.d(TAG, "listPostJSON $listPostJSON")
                    }
                } ?: run {
                    stateListPost.value = ProfileViewModelState.Failure("list null")
                }
            }
        })

    }
}
