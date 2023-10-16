package com.example.desh.mediaproviders.pictures.module

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumItem(val name: String, val isAll: Boolean, val bucketId: String) : Parcelable