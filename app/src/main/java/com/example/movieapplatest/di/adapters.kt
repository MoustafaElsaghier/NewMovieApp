package com.example.movieapplatest.di

import com.example.movieapplatest.ui.features.movies.adapters.MoviesAdapter
import org.koin.dsl.module

val adapters = module {
    // movies
    factory { MoviesAdapter() }
}