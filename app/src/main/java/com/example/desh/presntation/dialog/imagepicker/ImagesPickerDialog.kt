package com.example.desh.presntation.dialog.imagepicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.example.desh.databinding.SheetImagePickerBinding
import com.example.desh.mediaproviders.pictures.PicturesViewModel
import com.example.desh.presntation.dialog.imagepicker.adapter.AlbumsAdapter
import com.example.desh.presntation.dialog.imagepicker.adapter.ImagesAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ImagesPickerDialog : BottomSheetDialogFragment() {

    private lateinit var binding : SheetImagePickerBinding
    private lateinit var viewModel : PicturesViewModel
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SheetImagePickerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagesAdapter = ImagesAdapter()

        viewModel = ViewModelProvider(requireActivity())[PicturesViewModel::class.java]


        viewModel.picturesList.observe(viewLifecycleOwner){
            imagesAdapter.submitList(it)
            binding.picturesList.adapter = imagesAdapter
        }

        viewModel.albumsList.observe(viewLifecycleOwner){
            albumsAdapter = AlbumsAdapter(it, requireContext())
            binding.albumsSpinner.adapter = albumsAdapter
            binding.albumsSpinner.setSelection(0, false)
        }

        binding.albumsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, pos: Int, l: Long) {
                albumsAdapter.getItem(pos)?.bucketId?.let { viewModel.getPicturesByAlbum(it) }
            }
        }
    }



}