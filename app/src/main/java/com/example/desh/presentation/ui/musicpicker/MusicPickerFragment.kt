package com.example.desh.presentation.ui.musicpicker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desh.R
import com.example.desh.databinding.FragmentMusicPickerBinding

class MusicPickerFragment : Fragment() {

    private lateinit var bining: FragmentMusicPickerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bining = FragmentMusicPickerBinding.inflate(layoutInflater, container, false)
        return bining.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}