package com.example.desh.mediaproviders.pictures.model

class pictureFolderContent {

    var folderPath: String? = null
    var folderName: String? = null
    var photos: ArrayList<PictureContent>
    var bucket_id = 0

    constructor() {
        photos = ArrayList()
    }

    constructor(path: String?, folderName: String?) {
        folderPath = path
        this.folderName = folderName
        photos = ArrayList()
    }
}