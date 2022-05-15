package com.example.movieapplatest.ui.features.movies.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplatest.data.models.MovieModel
import com.example.movieapplatest.ui.features.movies.adapters.diffUtils.MovieModelDiffCallback
import com.example.movieapplatest.ui.features.movies.adapters.viewHolders.MoviesViewHolder
import io.reactivex.disposables.CompositeDisposable

class MoviesAdapter : ListAdapter<MovieModel, MoviesViewHolder>(MovieModelDiffCallback()) {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private lateinit var context: Context
    private var onMovieClickListener: ((item: MovieModel) -> Unit)? = null

    fun setOnMovieClickListener(onMovieClickListener: ((item: MovieModel) -> Unit)?) {
        this.onMovieClickListener = onMovieClickListener
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.context = recyclerView.context
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        disposable.clear()
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder.from(parent).apply {
            this.itemView.setOnClickListener {
                onMovieClickListener?.invoke(
                    getItem(adapterPosition)
                )
            }
        }
    }

}