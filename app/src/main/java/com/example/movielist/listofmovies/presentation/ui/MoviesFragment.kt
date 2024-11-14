package com.example.movielist.listofmovies.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.R
import com.example.movielist.databinding.FragmentMoviesBinding
import com.example.movielist.domain.dto.Movie
import com.example.movielist.listofmovies.presentation.viewmodel.MoviesViewModel
import com.example.movielist.utils.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding

    private val viewModel: MoviesViewModel by viewModel()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showToolbar()
        setupRecyclerView()
        viewModel.getMovies()
        setupObservers()
    }

    private fun showToolbar() {
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.movies_screen)
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter(
            itemClickListener = { movie ->
                onMovieItemClick(movie)
            },
            addToFavorite = { movie ->
                addToFavoriteClick(movie)
            },
            removeFromFavorite = { movie ->
                removeFromFavoriteClick(movie)
            }
        )
        with(binding?.moviesRecyclerView) {
            this?.let {
                layoutManager = LinearLayoutManager(context)
                adapter = moviesAdapter
            }
        }
    }

    private fun onMovieItemClick(movie: Movie) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(
            viewModel.getMovieDetails(movie)
        )
        findNavController().navigate(action)
    }

    private fun addToFavoriteClick(movie: Movie) {
        viewModel.insertMovie(viewModel.getMovieDetails(movie))
    }

    private fun removeFromFavoriteClick(movie: Movie) {
        viewModel.removeMovie(viewModel.getMovieDetails(movie))
    }

    private fun setupObservers() {
        collect(viewModel.moviesList, ::renderMovies)
        collect(viewModel.isLoading, ::modifyLoadingState)
        collect(viewModel.errorMessage, ::showError)
        collect(viewModel.isInsertedSuccessfully, ::getInsertResult)
        collect(viewModel.isDeletedSuccessfully, ::getDeleteResult)
    }

    private fun renderMovies(moviesList: List<Movie>?) {
        moviesAdapter.setMovies(moviesList ?: emptyList())
        binding?.animationNoDataAvailable?.visibility = View.GONE
    }

    private fun modifyLoadingState(isLoading: Boolean?) {
        binding?.progressBar?.isVisible = isLoading ?: true
    }

    private fun showError(errorMsg: String?) {
        errorMsg?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            binding?.animationNoDataAvailable?.visibility = View.VISIBLE
        }
    }

    private fun getInsertResult(isSuccess: Boolean?) {
        isSuccess?.let {
            if (isSuccess == true) {
                Toast.makeText(context, getString(R.string.inserted_successfully), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, getString(R.string.insertion_failed), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDeleteResult(isSuccess: Boolean?) {
        isSuccess?.let {
            if (isSuccess == true) {
                Toast.makeText(context, getString(R.string.deleted_successfully), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, getString(R.string.deletion_failed), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.removeIsInsertedSuccessfullyValue()
        viewModel.removeIsDeletedSuccessfullyValue()
    }
}