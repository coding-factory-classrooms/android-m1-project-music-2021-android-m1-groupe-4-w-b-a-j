package com.trap.project_music.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trap.project_music.server.service.APIAccount
import com.trap.project_music.ui.sign.viewmodel.SignUpViewModel

@Suppress("UNCHECKED_CAST")
class SignUpViewModelFactory(val apiAccount: APIAccount,val application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpViewModel(apiAccount,application) as T
    }

}