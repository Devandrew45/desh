package com.example.desh.mediaproviders.pictures

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.example.desh.mediaproviders.pictures.module.AlbumItem
import com.example.desh.mediaproviders.pictures.module.PictureContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("InlinedApi")
class PictureProvider(private val contentResolver: ContentResolver) {

    internal fun Cursor.doWhile(action: () -> Unit) {
        this.use {
            if (this.moveToFirst()) {
                do {
                    action()
                } while (this.moveToNext())
            }
        }
    }


    private val data = MediaStore.Images.Media.DATA
    private val displayName = MediaStore.Images.Media.DISPLAY_NAME
    private val size = MediaStore.Images.Media.SIZE
    private val albumBucketName = MediaStore.Images.Media.BUCKET_DISPLAY_NAME
    private val albumBucketID = MediaStore.Images.Media.BUCKET_ID
    private val _ID = MediaStore.Images.Media._ID
    private val dateTaken = MediaStore.Images.Media.DATE_TAKEN

    private val projections =
        arrayOf(data, displayName, size, albumBucketName, albumBucketID, _ID, dateTaken)

    private val externalContentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    private val internalContentUri = MediaStore.Images.Media.INTERNAL_CONTENT_URI
    private var cursor: Cursor? = null


    fun loadAlbums(): ArrayList<AlbumItem> {
        val albumCursor = contentResolver.query(
            externalContentUri,
            arrayOf(albumBucketName ,MediaStore.Images.ImageColumns.BUCKET_ID),
            null,
            null,
            MediaStore.Images.Media.DATE_TAKEN + " DESC"
        )

        val list = arrayListOf<AlbumItem>()
        try {
            list.add(AlbumItem("All", true,"0"))
            if (albumCursor == null) {
                return list
            }
            albumCursor.doWhile {
                val bucketId = albumCursor.getString(albumCursor.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_ID))
                val name = albumCursor.getString(albumCursor.getColumnIndex(albumBucketName)) ?: bucketId
                var albumItem = AlbumItem(name, false, bucketId)
                if (!list.contains(albumItem)) {
                    list.add(albumItem)
                }
            }
        } finally {
            if (albumCursor != null && !albumCursor.isClosed) {
                albumCursor.close()
            }
        }
        return list
    }


/*
    suspend fun loadAlbums(): ArrayList<AlbumItem> {

        val albumItems = arrayListOf<AlbumItem>()

        val albumCursor = contentResolver.query(
            externalContentUri,
            arrayOf(albumBucketName, albumBucketID),
            null,
            null,
            MediaStore.Images.Media.DATE_TAKEN + " DESC"
        )

        try {
            albumItems.add(AlbumItem("All", true, "0"))
            if (albumCursor == null) {
                return albumItems
            }

            do {
                val bucketId =
                    albumCursor.getString(albumCursor.getColumnIndexOrThrow(albumBucketID))
                val name = albumCursor.getString(albumCursor.getColumnIndexOrThrow(albumBucketName))
                    ?: bucketId

                val albumItem = AlbumItem(name, false, bucketId)
                if (!albumItems.contains(albumItem)) {
                    albumItems.add(albumItem)
                }
            } while (albumCursor.moveToNext())

        } finally {
            if (albumCursor != null && !albumCursor.isClosed) {
                albumCursor.close()
            }
        }
        return albumItems
    }
*/

    /**Returns an ArrayList of [PictureContent]   */
    fun getAllPictureContents(contentLocation: Uri?): ArrayList<PictureContent> {

        val images: ArrayList<PictureContent> = ArrayList()

        cursor = contentResolver.query(
            contentLocation!!,
            projections, null, null, "LOWER ($dateTaken) DESC"
        )

        try {
            cursor!!.moveToFirst()
            do {
                val pictureContent = PictureContent()
                pictureContent.pictureName =
                    cursor!!.getString(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                pictureContent.picturePath =
                    cursor!!.getString(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                pictureContent.pictureSize =
                    cursor!!.getLong(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
                val id =
                    cursor!!.getInt(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                pictureContent.pictureId = id

                val contentUri = Uri.withAppendedPath(contentLocation, id.toString())
                pictureContent.photoUri = contentUri.toString()

                images.add(pictureContent)
            } while (cursor!!.moveToNext())
            cursor!!.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return images
    }

    /**Returns an ArrayList of [PictureContent] in a specific folder */
    suspend fun getAllPictureContentByBucketID(bucketID: String): ArrayList<PictureContent> {

        val images: ArrayList<PictureContent> = ArrayList()

        cursor = contentResolver.query(
            externalContentUri,
            projections,
            "$albumBucketID like ? ", arrayOf("%$bucketID%"),
            "LOWER ($dateTaken) DESC"
        )

        try {
            cursor!!.moveToFirst()
            do {
                val pictureContent = PictureContent()

                pictureContent.pictureName = cursor!!.getString(cursor!!.getColumnIndexOrThrow(displayName))
                pictureContent.picturePath = cursor!!.getString(cursor!!.getColumnIndexOrThrow(data))
                pictureContent.pictureSize = cursor!!.getLong(cursor!!.getColumnIndexOrThrow(size))

                val id = cursor!!.getInt(cursor!!.getColumnIndexOrThrow(_ID))
                pictureContent.pictureId = id

                val contentUri = Uri.withAppendedPath(externalContentUri, id.toString())
                pictureContent.photoUri = contentUri.toString()

                images.add(pictureContent)

            } while (cursor!!.moveToNext())
            cursor!!.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return images
    }

    /* @get:SuppressLint("InlinedApi")
     val allPictureFolders: ArrayList<Any>
         */
    /**Returns an ArrayList of [pictureFolderContent]   *//*
        get() {
            val absolutePictureFolders: ArrayList<pictureFolderContent> =
                ArrayList<pictureFolderContent>()
            val picturePaths = ArrayList<Int>()
            cursor = pictureContext.contentResolver.query(
                externalContentUri, projections, null, null,
                "LOWER (" + MediaStore.Images.Media.DATE_TAKEN + ") DESC"
            )
            try {
                cursor!!.moveToFirst()
                do {
                    val photoFolder = pictureFolderContent()
                    val pictureContent = PictureContent()
                    pictureContent.setPictureName(
                        cursor!!.getString(
                            cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                        )
                    )
                    pictureContent.setPicturePath(
                        cursor!!.getString(
                            cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                        )
                    )
                    pictureContent.setPictureSize(
                        cursor!!.getLong(
                            cursor!!.getColumnIndexOrThrow(
                                MediaStore.Images.Media.SIZE
                            )
                        )
                    )
                    val id =
                        cursor!!.getInt(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                    pictureContent.setPictureId(id)
                    pictureContent.setPhotoUri(
                        Uri.withAppendedPath(
                            externalContentUri, id.toString()
                        ).toString()
                    )
                    val folder =
                        cursor!!.getString(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                    val datapath = cursor!!.getString(
                        cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    )
                    val bucket_id =
                        cursor!!.getInt(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID))
                    var folderpaths = datapath.substring(
                        0, datapath.lastIndexOf(
                            "$folder/"
                        )
                    )
                    folderpaths = "$folderpaths$folder/"
                    if (!picturePaths.contains(bucket_id)) {
                        picturePaths.add(bucket_id)
                        photoFolder.setBucket_id(bucket_id)
                        photoFolder.setFolderPath(folderpaths)
                        photoFolder.setFolderName(folder)
                        photoFolder.getPhotos().add(pictureContent)
                        absolutePictureFolders.add(photoFolder)
                    } else {
                        for (folderX in absolutePictureFolders) {
                            if (folderX.getBucket_id() === bucket_id) {
                                folderX.getPhotos().add(pictureContent)
                            }
                        }
                    }
                } while (cursor!!.moveToNext())
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return absolutePictureFolders
        }
    *//* suspend fun getImages(count: Int, start: Int): Three<MutableList<Three<Uri?, String?, Date>>, Boolean, Int> {
               val imagesList = mutableListOf<Three<Uri?, String?, Date>>()
               var index = start

               return withContext(Dispatchers.IO) {
                   while (imageCursor?.moveToPosition(i) == true) {
                       val id = imageIdColumn?.let { imageCursor.getLong(it) }
                       val dateModified = Date(TimeUnit.SECONDS.toMillis(imageCursor.getLong(imageDateModifiedColumn ?: 0)))

                       val displayName = imageDisplayNameColumn?.let { imageCursor.getString(it) }
                       val contentUri = id?.let {
                           ContentUris.withAppendedId(
                                   MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                   it
                           )
                       }
                       index++
                       contentUri?.let { imagesList.add(Three(contentUri, displayName, dateModified)) }
                       if (index == count)
                           break
                   }
                   val areAllLoaded = index == imagesToLoad
                   return@withContext Three(imagesList, areAllLoaded, index)
               }
           }*//*
*/

}