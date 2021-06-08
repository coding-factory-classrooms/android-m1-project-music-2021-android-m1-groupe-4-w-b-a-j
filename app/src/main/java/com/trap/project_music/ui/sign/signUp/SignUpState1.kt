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
import com.trap.project_music.factory.SignUpViewModelFactory
import com.trap.project_music.server.RetrofitFactory
import com.trap.project_music.server.service.APIAccount
import com.trap.project_music.ui.sign.viewmodel.SignUpViewModel
import com.trap.project_music.ui.sign.viewmodel.SignUpViewModelFirstState
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpState1 : Fragment() {

    val viewModel: SignUpViewModel by navGraphViewModels(R.id.signup_graph){
        SignUpViewModelFactory(RetrofitFactory(requireContext()).createService(APIAccount::class.java),requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createAccount.formatHtml(getString(R.string.createYourAccount))
        en_appuyant.formatHtml(getString(R.string.en_appuyant))

        usernameSignUp.addTextChangedListener(onUsernameChange)
        email.addTextChangedListener(onEmailChange)
        btn_signUp1.isClickable = false

        viewModel.getFirstState().observe(viewLifecycleOwner, Observer { updateUI(it) })

        btn_signUp1.setOnClickListener {findNavController().navigate(R.id.action_signUp_to_signUpState2)}
        btn_gologin.setOnClickListener {findNavController().navigate(R.id.action_signUp_to_loginFragment)}
    }

    private val onUsernameChange : TextWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            viewModel.changeName(s.toString())
        }
    }
    private val onEmailChange : TextWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            viewModel.changeEmail(s.toString())
        }
    }

    private fun updateUI(state: SignUpViewModelFirstState){
        when(state){
            is SignUpViewModelFirstState.Success -> {
                btn_signUp1.isClickable = true
                emailParent.error = ""
                usernameParent.error = ""
            }
            is SignUpViewModelFirstState.Error ->{
                btn_signUp1.isClickable = false
                if(!state.emailIsValid){
                    emailParent.error = "used"
                }
                if(!state.nameIsValid){
                    usernameParent.error = "used"
                }
            }
            is SignUpViewModelFirstState.Initial ->{
                btn_signUp1.isClickable = false
            }
        }
    }
}
