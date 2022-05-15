package com.example.movieapplatest.di

import android.content.Context
import com.example.movieapplatest.utils.ResourcesUtil

import org.koin.dsl.module

val utilModule = module {
    single { ResourcesUtil(get() as Context) }
}

