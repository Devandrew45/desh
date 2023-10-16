package com.example.desh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.desh.presntation.dialog.VerificationDialog

class SingInFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_sing_in, container, false)

        val o =  view.findViewById<TextView>(R.id.btn_forget_password)
        o.setOnClickListener {
            VerificationDialog(requireActivity()).apply {
                setOnClickListener {
                    dismiss()
                    val action = SingInFragmentDirections.actionSingInFragmentToForgetPasswordFragment(it)
                    findNavController().navigate(action)
                }
            }.show()

        }


        val signup = view.findViewById<TextView>(R.id.btn_create_acc)
        signup.setOnClickListener {
            findNavController().navigate(R.id.action_singInFragment_to_signUpFragment)
        }
        return view
    }

}