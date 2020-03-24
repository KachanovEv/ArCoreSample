package com.example.ar_core_sample.di

import androidx.room.Room
import com.example.ar_core_sample.data.db.ArCoreDataBase
import com.example.ar_core_sample.ui.fragment.ar_scene.ScanningImagesViewModel
import com.example.ar_core_sample.ui.fragment.library.LibraryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(get(), ArCoreDataBase::class.java, "arCore-database").build()
    }

    single { get<ArCoreDataBase>().imageFromLibraryDao() }

    // viewModel
    viewModel { ScanningImagesViewModel(get()) }
    viewModel { LibraryViewModel(get()) }
}