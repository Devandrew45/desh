package com.example.desh.presentation.ui.imagepicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.desh.databinding.FragmentChooseCoverPictureBinding
import com.example.desh.mediaproviders.pictures.PicturesViewModel
import com.example.desh.presentation.ui.imagepicker.adapter.AlbumsAdapter
import com.example.desh.presentation.ui.imagepicker.adapter.ImagesAdapter

class CoverPickerFragment : Fragment() {

    private lateinit var binding : FragmentChooseCoverPictureBinding
    private lateinit var viewModel : PicturesViewModel
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChooseCoverPictureBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagesAdapter = ImagesAdapter()
        viewModel = ViewModelProvider(requireActivity())[PicturesViewModel::class.java]
        observer()

        (binding.picturesList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        imagesAdapter.setOnPictureListener {

        }



        binding.albumsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, pos: Int, l: Long) {
                albumsAdapter.getItem(pos)?.bucketId?.let { viewModel.getPicturesByAlbum(it) }
            }
        }
    }




    private fun observer(){
        viewModel.picturesList.observe(viewLifecycleOwner){
            imagesAdapter.submitList(it)
            binding.picturesList.adapter = imagesAdapter
        }

        viewModel.albumsList.observe(viewLifecycleOwner){
            albumsAdapter = AlbumsAdapter(it, requireContext())
            binding.albumsSpinner.adapter = albumsAdapter
            binding.albumsSpinner.setSelection(0, false)
        }
    }



}