package com.example.desh.mediaproviders.pictures.module

data class PictureContent(
    var pictureName: String? = null,
    var picturePath: String? = null,
    var pictureSize: Long? = null,
    var photoUri: String? = null,
    var pictureId: Int? = 0,
    var isSelected : Boolean?= false
)