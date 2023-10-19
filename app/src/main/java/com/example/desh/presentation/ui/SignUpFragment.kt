package com.example.desh.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.desh.R
import com.example.desh.databinding.FragmentSignUpBinding
import com.example.desh.presentation.ui.imagepicker.ImagesPickerDialog

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            signUpTb.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            imageView4.setOnClickListener {
               val bottomSheetFragment = ImagesPickerDialog()

                bottomSheetFragment.show(requireActivity().supportFragmentManager, "BSDialogFragment")
            }
        }
    }
}