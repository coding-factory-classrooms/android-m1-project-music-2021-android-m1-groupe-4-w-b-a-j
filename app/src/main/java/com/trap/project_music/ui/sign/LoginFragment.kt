package com.trap.project_music.ui.sign

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.trap.project_music.R
import com.trap.project_music.common.formatHtml
import com.trap.project_music.common.makeToast
import com.trap.project_music.dal.DatabaseFactory
import com.trap.project_music.dal.dao.AccountDAO
import com.trap.project_music.factory.LoginViewModelFactory
import com.trap.project_music.server.RetrofitFactory
import com.trap.project_music.server.service.APIAccount
import com.trap.project_music.ui.sign.viewmodel.LoginFragmentState
import com.trap.project_music.ui.sign.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.btn_gosignup
import kotlinx.android.synthetic.main.login_fragment.email


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_gosignup.setOnClickListener {findNavController().navigate(R.id.action_loginFragment_to_signUp)}
        val accountDAO :AccountDAO = DatabaseFactory.create(requireContext()).accountDAO()
        welcomeback.formatHtml(getString(R.string.welcome_back))

        loginViewModel = ViewModelProvider(viewModelStore,LoginViewModelFactory(RetrofitFactory(requireContext())
            .createService(APIAccount::class.java), activity?.application!!
        )).get(LoginViewModel::class.java)

        loginViewModel.getState().observe(viewLifecycleOwner, Observer { updateUI(it) })

        btn_signIn.setOnClickListener {
            loginViewModel.connection(username.text.toString(), email.text.toString())
        }
    }

    private fun updateUI(state: LoginFragmentState){
        when(state){
            is LoginFragmentState.Failure ->{
                makeToast("Error")
            }
            is LoginFragmentState.Loading ->{
                makeToast("Loading")
            }
            is LoginFragmentState.Success ->{
                findNavController().navigate(R.id.action_global_homeFragment2)
            }
        }
    }



}
