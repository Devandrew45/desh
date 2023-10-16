package com.example.desh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.desh.presntation.dialog.imagepicker.ImagesPickerDialog


class SignUpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)


        val image = view.findViewById<ImageView>(R.id.imageView4)
        image.setOnClickListener {
           val  bottomSheetFragment = ImagesPickerDialog()
            bottomSheetFragment.show(requireActivity().supportFragmentManager, "BSDialogFragment")
        }

        return view
    }
}