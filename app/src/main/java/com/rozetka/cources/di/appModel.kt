package com.rozetka.cources.di

import com.rozetka.cources.ui.MainActivityViewModel
import com.rozetka.cources.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MainActivityViewModel(get()) }
}
