package com.example.desh.mediaproviders.pictures

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.desh.mediaproviders.pictures.model.AlbumItem
import com.example.desh.mediaproviders.pictures.model.PictureContent
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

class PicturesViewModel(application: Application) : AndroidViewModel(application) {

    private var pictureProvider: PictureProvider

    val picturesList = MutableLiveData<List<PictureContent>>()
    val albumsList = MutableLiveData<List<AlbumItem>>()

    init {
        pictureProvider = PictureProvider(application.contentResolver)
        getAlbums()
    }

    fun getPicturesByAlbum(bucketID: String) {
        viewModelScope.launch(EmptyCoroutineContext, CoroutineStart.DEFAULT) {
            delay(500L)
            picturesList.postValue(pictureProvider.getAllPictureContentByBucketID(bucketID))
        }
    }

    private fun getAlbums() {
        viewModelScope.launch(EmptyCoroutineContext, CoroutineStart.DEFAULT) {
            albumsList.postValue(pictureProvider.loadAlbums())
        }
    }


}