package com.trap.project_music.ui.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trap.project_music.enums.VoteType
import com.trap.project_music.model.Vote
import com.trap.project_music.server.service.APIPost
import com.trap.project_music.vo.PageJSON
import com.trap.project_music.vo.PostJSON
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

sealed class HomeViewModelState() {

    data class Succes(val listPostJSON: List<PostJSON>) : HomeViewModelState()
    data class Loading(val message: String) : HomeViewModelState()
    data class Failure(val errorMessage: String) : HomeViewModelState()

}

class HomeViewModel(private val apiPost: APIPost) : ViewModel() {

    private val state = MutableLiveData<HomeViewModelState>()

    private var actualPage = 0;

    private val actualPost = MutableLiveData<PostJSON>()
    fun actualPost(): LiveData<PostJSON> = actualPost
    private var listPostJSON: ArrayList<PostJSON> = ArrayList()

    private val stateListPost = MutableLiveData<HomeViewModelState>()
    fun getState(): LiveData<HomeViewModelState> = state
    fun getStateListPost(): LiveData<HomeViewModelState> = stateListPost

    fun getPosts2() {
        stateListPost.value = HomeViewModelState.Loading("Chargement : ")
        val serviceRequest = apiPost.postsPage(actualPage)
        serviceRequest.enqueue(object : Callback<PageJSON> {
            override fun onFailure(call: Call<PageJSON>, t: Throwable) {
                Log.v("TEST", "FAILURE $t")
                stateListPost.value = HomeViewModelState.Failure("Error")
            }

            override fun onResponse(
                call: Call<PageJSON>,
                response: Response<PageJSON>
            ) {
                response.body()?.also { it ->
                    stateListPost.value = HomeViewModelState.Succes(it.content)

                    listPostJSON.addAll(it.content)

                    // set actualpost to first post

                    if (listPostJSON.isNotEmpty()) {
                        actualPost.postValue(listPostJSON[0])
                    }
                    actualPage += 1
                } ?: run {
                    stateListPost.value = HomeViewModelState.Failure("list null")
                }
            }
        })

    }


    fun getPosts() {
        stateListPost.value = HomeViewModelState.Loading("Chargement : ")
        val serviceRequest = apiPost.posts()
        serviceRequest.enqueue(object : Callback<List<PostJSON>> {
            override fun onFailure(call: Call<List<PostJSON>>, t: Throwable) {
                Log.v("TEST", "FAILURE $t")
                stateListPost.value = HomeViewModelState.Failure("Error")
            }

            override fun onResponse(
                call: Call<List<PostJSON>>,
                response: Response<List<PostJSON>>
            ) {
                response.body()?.also { it ->

                    Log.d("RESPONSE", "POST_LIST $it")

                    stateListPost.value = HomeViewModelState.Succes(it)

                    listPostJSON.addAll(it)

                    // set actualpost to first post

                    if (listPostJSON.isNotEmpty()) {
                        actualPost.postValue(listPostJSON[0])
                    }
                    actualPage += 1
                } ?: run {
                    stateListPost.value = HomeViewModelState.Failure("list null")
                }
            }
        })

    }

    fun sendVote(voteType: VoteType, idPost: Long) {
        stateListPost.value = HomeViewModelState.Loading("Chargement : ")

        val serviceRequest = apiPost.vote(voteType, idPost)
        serviceRequest.enqueue(object : Callback<Vote> {
            override fun onFailure(call: Call<Vote>, t: Throwable) {
                stateListPost.value = HomeViewModelState.Failure("Error")
            }

            override fun onResponse(call: Call<Vote>, response: Response<Vote>) {
                if (response.code() == 201) {
                    Log.v("test", "vote : ${response.body()}")
                } else {
                    stateListPost.value = HomeViewModelState.Failure("list null")
                }
            }

        })
    }

    fun changeActualPost(position: Int) {
        actualPost.postValue(listPostJSON[position])
        if (position > (listPostJSON.size - 5)) getPosts()
    }

}




