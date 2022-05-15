package com.example.movieapplatest.ui.features.movies.fragments

import com.example.movieapplatest.R
import com.example.movieapplatest.databinding.MovieDetailsFragmentBinding
import com.example.movieapplatest.ui.base.BaseFragment
import com.example.movieapplatest.ui.features.movies.MoviesVM
import timber.log.Timber

class MoviesDetailsFragment : BaseFragment<MovieDetailsFragmentBinding, MoviesVM>(MoviesVM::class) {

    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun getLayoutId(): Int = R.layout.movie_details_fragment
    override fun initFragment() {

    }

    override fun initLiveData() {
        super.initLiveData()
        val bundle = arguments
        if (bundle == null) {
            Timber.e("Movies details", "MoviesDetailsFragment did not receive movieId")
            return
        }

        val args = MoviesDetailsFragmentArgs.fromBundle(bundle)
        loading(true)
        viewModel.loadMoviesDetails(getString(R.string.api_key), args.movieId)
        viewModel.movieLiveData.observe(viewLifecycleOwner) {
            loading(false)
            binding.movieModel = it
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.movieLiveData.value = null
    }
}