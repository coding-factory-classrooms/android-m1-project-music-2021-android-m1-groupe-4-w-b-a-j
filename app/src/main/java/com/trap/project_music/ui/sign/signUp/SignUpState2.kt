package com.trap.project_music.ui.sign.signUp

import android.os.Build
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer

import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.trap.project_music.R
import com.trap.project_music.common.formatHtml
import com.trap.project_music.common.makeToast
import com.trap.project_music.factory.SignUpViewModelFactory
import com.trap.project_music.model.GenderType
import com.trap.project_music.server.RetrofitFactory
import com.trap.project_music.server.service.APIAccount
import com.trap.project_music.ui.sign.viewmodel.SignUpViewModel

import com.trap.project_music.ui.sign.viewmodel.SignUpViewModelSecondState

import kotlinx.android.synthetic.main.fragment_sign_up_state2.*
import java.time.LocalDate

class SignUpState2 : Fragment() {

    // creation d'un sharedViewmodel
    val viewModel: SignUpViewModel by navGraphViewModels(R.id.signup_graph){
        SignUpViewModelFactory(RetrofitFactory(requireContext()).createService(APIAccount::class.java),requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up_state2, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, resources.getStringArray(R.array.genres))
        gender.setAdapter(adapter)

        gender.setOnItemClickListener { parent, view, position, id -> viewModel.changeGender(GenderType.values()[position]) }
        datePicker1.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth -> viewModel.setBirthdate(LocalDate.of(year,monthOfYear,dayOfMonth)) }

        viewModel.getSecondState().observe(viewLifecycleOwner, Observer { updateUI(it) })

        moreAboutYou.formatHtml(getString(R.string.moreAboutYou))
        next1.setOnClickListener {findNavController().navigate(R.id.action_signUpState2_to_signUpState3)}
    }

    private fun updateUI(state: SignUpViewModelSecondState){
        when(state){
            is SignUpViewModelSecondState.Success -> {
                next1.isClickable = true
            }
            is SignUpViewModelSecondState.Error ->{
                next1.isClickable = false
            }
        }

    }
}
