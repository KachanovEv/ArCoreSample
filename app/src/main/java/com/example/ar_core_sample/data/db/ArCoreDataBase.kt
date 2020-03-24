package com.example.ar_core_sample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ar_core_sample.data.db.dao.ImageFromLibraryDao
import com.example.ar_core_sample.data.db.model_db.ImageUriEntity

@Database(
    entities = [ImageUriEntity::class], version = 1
)
abstract class ArCoreDataBase : RoomDatabase() {
    abstract fun imageFromLibraryDao(): ImageFromLibraryDao
}