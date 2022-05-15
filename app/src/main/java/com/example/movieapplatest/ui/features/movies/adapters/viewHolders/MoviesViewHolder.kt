package com.example.movieapplatest.ui.features.movies.adapters.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplatest.R
import com.example.movieapplatest.data.models.MovieModel
import com.example.movieapplatest.databinding.ItemMovieBinding

class MoviesViewHolder constructor(private val viewBinding: ItemMovieBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(
        item: MovieModel
    ) {
        viewBinding.movieModel = item
    }

    //For inflating the layout in onCreateViewHolder()
    companion object {
        fun from(parent: ViewGroup): MoviesViewHolder {
            return MoviesViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_movie,
                    parent,
                    false
                )
            )
        }
    }
}