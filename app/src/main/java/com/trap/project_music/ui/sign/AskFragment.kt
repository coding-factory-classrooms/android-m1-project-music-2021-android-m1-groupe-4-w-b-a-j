package com.trap.project_music.ui.sign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.trap.project_music.R
import kotlinx.android.synthetic.main.ask_fragment.*

class AskFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.ask_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_login.setOnClickListener { findNavController().navigate(R.id.action_askFragment_to_loginFragment) }
    }

}
