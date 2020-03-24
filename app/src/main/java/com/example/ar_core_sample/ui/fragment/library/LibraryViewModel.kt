package com.example.ar_core_sample.ui.fragment.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ar_core_sample.data.db.ArCoreDataBase
import com.example.ar_core_sample.data.db.model_db.ImageUriEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LibraryViewModel(application: Application) : AndroidViewModel(application) {

    val imageUriLiveData: MutableLiveData<List<ImageUriEntity>> = MutableLiveData()

    fun insertHydrationHistory(
        arCoreDataBase: ArCoreDataBase,
        imageUri: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            arCoreDataBase.imageFromLibraryDao().insert(ImageUriEntity(imageUri.hashCode(),imageUri))
        }
    }

    fun getAllImagesUri(arCoreDataBase: ArCoreDataBase){
        viewModelScope.launch(Dispatchers.IO) {
            val result = arCoreDataBase.imageFromLibraryDao().getAllImagesUri()
            withContext(Main){
                imageUriLiveData.value = result
            }
        }
    }
}