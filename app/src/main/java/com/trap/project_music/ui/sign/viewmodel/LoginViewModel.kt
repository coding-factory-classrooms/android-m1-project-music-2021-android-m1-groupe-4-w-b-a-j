package com.trap.project_music.ui.sign.viewmodel

import android.accounts.Account
import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Parcel
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trap.project_music.dal.dao.AccountDAO
import com.trap.project_music.dal.entity.AccountInfo
import com.trap.project_music.server.model.Auth
import com.trap.project_music.server.service.APIAccount
import com.trap.project_music.ui.sign.viewmodel.service.ILoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


sealed class LoginFragmentState {

    data class Success(val message : String): LoginFragmentState()
    data class Loading(val message : String): LoginFragmentState()
    data class Failure(val errorMessage: String) : LoginFragmentState()

}

class LoginViewModel(private val apiAccount: APIAccount, application: Application) : AndroidViewModel(application), ILoginViewModel {

    private var state: MutableLiveData<LoginFragmentState> = MutableLiveData()

    override fun getState(): LiveData<LoginFragmentState> = state

    override fun connection(userName : String, password: String){
        state.postValue(LoginFragmentState.Loading(""))
        apiAccount.getToken(Auth.Request()).enqueue(object : Callback<Auth.Response>{
            override fun onFailure(call: Call<Auth.Response>, t: Throwable) {
                state.postValue(LoginFragmentState.Failure("${t.message}"))
                Log.v("test","error : ${t.message}")
            }

            @SuppressLint("CommitPrefEdits")
            override fun onResponse(call: Call<Auth.Response>, response: Response<Auth.Response>) {
                Log.d("test", "token LOGIN_VIEW_MODEL  response.body() : ${response.body()} / RESPONSE.CODE : ${response.code()}}");

                if(response.code() == 401){
                    state.postValue(LoginFragmentState.Failure("401"))
                }else{
                    getApplication<Application>().getSharedPreferences("ACCOUNT",Context.MODE_PRIVATE).edit().putString("authToken",response.body()?.token).apply()
                    Log.d("test", "token LOGIN_VIEW_MODEL ${response.body()?.token}");
                    state.postValue(LoginFragmentState.Success(response.body()?.token.toString()))
                }
            }

        })
    }
}

