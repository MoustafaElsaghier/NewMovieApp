package com.example.movieapplatest.ui.features.movies.adapters.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapplatest.data.models.MovieModel

class MovieModelDiffCallback : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        //the one in your model
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }
}