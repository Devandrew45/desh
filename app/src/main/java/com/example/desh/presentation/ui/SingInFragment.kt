package com.example.desh.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.desh.R
import com.example.desh.domain.enums.VerificationType

class SingInFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_sing_in, container, false)

        val o =  view.findViewById<TextView>(R.id.btn_forget_password)
        o.setOnClickListener {
            val action = SingInFragmentDirections.actionSingInFragmentToForgetPasswordFragment(VerificationType.EMAIL)
            findNavController().navigate(action)
        }

        val signup = view.findViewById<TextView>(R.id.btn_create_acc)
        signup.setOnClickListener {
            findNavController().navigate(R.id.action_singInFragment_to_signUpFragment)
        }
        return view
    }

}