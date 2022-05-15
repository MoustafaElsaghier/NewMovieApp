package com.example.movieapplatest.data.repo

import com.example.movieapplatest.data.models.MovieModel
import com.example.movieapplatest.data.models.MoviesListResponse
import com.example.movieapplatest.data.remote.NetworkConstants
import com.example.movieapplatest.data.remote.NetworkManager
import io.reactivex.Observable

class MoviesRepo(private val networkManager: NetworkManager) {

    fun getMoviesList(apiKey: String): Observable<MoviesListResponse> {
        val params: HashMap<String, Any> = HashMap()
        params["api_key"] = apiKey
        return networkManager.getRequest(
            NetworkConstants.MOVIES_URL,
            HashMap(),
            params,
            MoviesListResponse::class.java
        )
    }

    fun getMoviesDetails(apiKey: String, movieId: String): Observable<MovieModel> {
        val params: HashMap<String, Any> = HashMap()
        params["api_key"] = apiKey
        return networkManager.getRequest(
            NetworkConstants.MOVIES_DETAILS_URL + movieId,
            HashMap(),
            params,
            MovieModel::class.java
        )
    }
}