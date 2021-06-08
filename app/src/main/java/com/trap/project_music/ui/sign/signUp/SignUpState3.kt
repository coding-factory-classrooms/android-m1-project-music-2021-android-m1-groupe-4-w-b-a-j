package com.trap.project_music.ui.sign.signUp

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.trap.project_music.R
import com.trap.project_music.common.formatHtml
import com.trap.project_music.common.makeToast
import com.trap.project_music.factory.SignUpViewModelFactory
import com.trap.project_music.server.RetrofitFactory
import com.trap.project_music.server.service.APIAccount
import com.trap.project_music.ui.sign.viewmodel.SignUpViewModel
import com.trap.project_music.ui.sign.viewmodel.SignUpViewModelThirdState
import kotlinx.android.synthetic.main.fragment_sign_up_state3.*


class SignUpState3 : Fragment() {

    // creation d'un sharedViewmodel
    val viewModel: SignUpViewModel by navGraphViewModels(R.id.signup_graph){
        SignUpViewModelFactory(RetrofitFactory(requireContext()).createService(APIAccount::class.java),requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up_state3, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        password1_r.addTextChangedListener(onPasswordChange)
        password2_r.addTextChangedListener(onConfirmPasswordChange)

        choosePassword.formatHtml(getString(R.string.choosePassword))
        viewModel.getThirdState().observe(viewLifecycleOwner, Observer { updateUI(it) })
        viewModel.isAccountCreated().observe(viewLifecycleOwner, Observer {
            if(it){
                findNavController().navigate(R.id.action_global_homeFragment2)
            }else{
                makeToast("error")
            }
        })

        finish.setOnClickListener {viewModel.createAccount()}
    }

    private val onPasswordChange : TextWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int,
                                       count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            viewModel.password = s.toString()
        }
    }
    private val onConfirmPasswordChange : TextWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int,
                                       count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            viewModel.confirmPassword = s.toString()
        }
    }

    private fun updateUI(state: SignUpViewModelThirdState){
        when(state){
            is SignUpViewModelThirdState.Success -> {
                finish.isClickable = true
                password1.error = ""
                password2.error = ""
            }
            is SignUpViewModelThirdState.Error ->{
                finish.isClickable = false
                if(!state.isPasswordValid){
                    password1.error = "invalid"
                }
                if(!state.isConfirmValid){
                    password2.error = "not same"
                }
            }
            is SignUpViewModelThirdState.Initial ->{
                finish.isClickable = false
            }
        }
    }
}
