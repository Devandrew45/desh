package com.example.desh.presntation.dialog.imagepicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.desh.databinding.SheetImagePickerBinding
import com.example.desh.mediaproviders.pictures.PicturesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ImagesPickerDialog : BottomSheetDialogFragment() {

    private lateinit var binding : SheetImagePickerBinding
    private lateinit var viewModel : PicturesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SheetImagePickerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     //   viewModel = ViewModelProviders
    }


}