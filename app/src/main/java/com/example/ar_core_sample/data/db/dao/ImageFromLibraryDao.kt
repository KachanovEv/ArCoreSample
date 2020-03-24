package com.example.ar_core_sample.data.db.dao

import android.net.Uri
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ar_core_sample.data.db.model_db.ImageUriEntity


@Dao
interface ImageFromLibraryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imageUri: ImageUriEntity)

    @Query("SELECT * FROM image_uri")
    fun getAllImagesUri(): List<ImageUriEntity>
}