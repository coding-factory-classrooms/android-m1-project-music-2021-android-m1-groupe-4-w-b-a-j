package com.trap.project_music.ui.sign.viewmodel.service

import androidx.lifecycle.LiveData
import com.trap.project_music.ui.sign.viewmodel.LoginFragmentState

interface ILoginViewModel {
    fun getState() : LiveData<LoginFragmentState>
    fun connection(userName : String, password: String)
}