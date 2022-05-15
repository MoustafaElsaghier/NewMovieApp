package com.example.movieapplatest.di

import com.example.movieapplatest.data.repo.MoviesRepo
import com.example.movieapplatest.ui.features.movies.MoviesVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesVM(
            get() as MoviesRepo,
        )
    }

}