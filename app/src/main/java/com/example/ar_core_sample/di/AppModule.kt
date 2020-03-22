package com.example.ar_core_sample.di

import com.example.ar_core_sample.ui.fragment.ar_scene.ArSceneViewModel
import com.example.ar_core_sample.ui.fragment.library.LibraryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // viewModel
    viewModel { ArSceneViewModel(get()) }
    viewModel { LibraryViewModel(get()) }
}