package com.trap.project_music.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trap.project_music.server.service.APIAccount
import com.trap.project_music.ui.sign.viewmodel.LoginViewModel

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val apiAccount: APIAccount, private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(apiAccount,application) as T
    }
}