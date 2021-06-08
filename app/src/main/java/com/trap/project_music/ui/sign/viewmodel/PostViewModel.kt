package com.trap.project_music.ui.sign.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.trap.project_music.server.service.APIPost
import com.trap.project_music.vo.PostJSON
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostViewModel(private val apiPost: APIPost) : ViewModel() {





    fun getPosts(){
        val serviceRequest = apiPost.posts()
        serviceRequest.enqueue(object : Callback<List<PostJSON>> {
            override fun onFailure(call: Call<List<PostJSON>>, t: Throwable) {
                Log.v("TEST","FAILURE $t")
                //stateListPost.value = HomeViewModelState.Failure("Error")
            }
            override fun onResponse(call: Call<List<PostJSON>>, response: Response<List<PostJSON>>) {
                Log.v("TEST","SUCCES $response")
                if(response.body() != null){
                    //stateListPost.value = HomeViewModelState.Succes(response.body()!!)
                }else{
                    //stateListPost.value = HomeViewModelState.Failure("list null")
                }

            }
        })

    }

}