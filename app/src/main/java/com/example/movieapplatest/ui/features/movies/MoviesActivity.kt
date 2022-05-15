package com.example.movieapplatest.ui.features.movies

import com.example.movieapplatest.R
import com.example.movieapplatest.databinding.ActivityMoviesBinding
import com.example.movieapplatest.ui.base.BaseActivity

class MoviesActivity : BaseActivity<ActivityMoviesBinding, MoviesVM>(MoviesVM::class) {

    override fun getLayoutId(): Int = R.layout.activity_movies

    override fun initActivity() {

    }
}