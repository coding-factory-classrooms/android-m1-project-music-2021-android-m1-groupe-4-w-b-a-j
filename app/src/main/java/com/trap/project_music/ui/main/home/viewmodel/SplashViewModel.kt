package com.trap.project_music.ui.main.home.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trap.project_music.server.service.APIAccount
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashViewModel(application: Application, private val apiAccount: APIAccount) : AndroidViewModel(application) {
    private var _isConnected : MutableLiveData<Boolean> = MutableLiveData()
    fun getConnected(): LiveData<Boolean> = _isConnected

    fun isConnected(){
        val token : String? = getApplication<Application>().getSharedPreferences("ACCOUNT",Context.MODE_PRIVATE).getString("authToken",null)
        if(token != null){
            Log.v("test","token $token")
            apiAccount.checkToken().enqueue(object  : Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.v("test","fail : $t")
                    _isConnected.postValue(false)
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.v("test","code ${response.code()} value : ${response.code() == 200}")
                    _isConnected.postValue(response.code() == 204)
                }

            })
        }else{
            Log.v("test","null")
            _isConnected.postValue(false)
        }

    }
}