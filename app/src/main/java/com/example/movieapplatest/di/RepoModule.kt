package com.example.movieapplatest.di

import com.example.movieapplatest.data.remote.NetworkManager
import com.example.movieapplatest.data.repo.MoviesRepo
import org.koin.dsl.module

val repoModule = module {
    single {
        MoviesRepo(
            get() as NetworkManager
        )
    }

}

