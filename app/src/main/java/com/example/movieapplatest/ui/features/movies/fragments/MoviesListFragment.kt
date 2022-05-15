package com.example.movieapplatest.ui.features.movies.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.movieapplatest.R
import com.example.movieapplatest.databinding.MoviesListFragmentBinding
import com.example.movieapplatest.ui.base.BaseFragment
import com.example.movieapplatest.ui.features.movies.MoviesVM
import com.example.movieapplatest.ui.features.movies.adapters.MoviesAdapter
import org.koin.android.ext.android.inject

class MoviesListFragment : BaseFragment<MoviesListFragmentBinding, MoviesVM>(MoviesVM::class) {

    private val moviesAdapter: MoviesAdapter by inject()

    override fun getLayoutId(): Int = R.layout.movies_list_fragment

    override fun initFragment() {
        loading(true)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        moviesAdapter.setOnMovieClickListener {
            findNavController().navigate(
                R.id.action_FirstFragment_to_SecondFragment,
                Bundle().apply {
                    putInt("movieId", it.id)
                })
        }
        binding.moviesRecyclerView.adapter = moviesAdapter
    }

    override fun initLiveData() {
        super.initLiveData()
        viewModel.loadMoviesList(getString(R.string.api_key))
        viewModel.moviesResponseLiveData.observe(viewLifecycleOwner) {
            loading(false)

            if (!it.isNullOrEmpty())
                moviesAdapter.submitList(it)
            else
                Toast.makeText(context, "there's error loading movies list", Toast.LENGTH_SHORT)
                    .show()
        }
    }
}