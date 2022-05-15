package com.example.movieapplatest.ui.features.movies

import androidx.lifecycle.MutableLiveData
import com.example.movieapplatest.data.models.MovieModel
import com.example.movieapplatest.data.repo.MoviesRepo
import com.example.movieapplatest.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesVM(
    private val moviesRepo: MoviesRepo,
) : BaseViewModel() {

    val moviesResponseLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData()
    val movieLiveData: MutableLiveData<MovieModel> = MutableLiveData()

    fun loadMoviesList(apiKey: String) {
        addToDisposable(
            moviesRepo.getMoviesList(apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    moviesResponseLiveData.value = it.results
                }, { processError(it) })
        )
    }

    fun loadMoviesDetails(apiKey: String, movieId: Int) {
        addToDisposable(
            moviesRepo.getMoviesDetails(apiKey, movieId.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieLiveData.value = it
                }, { processError(it) })
        )
    }


}