package com.example.movielist.moviedetails.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.movielist.R
import com.example.movielist.databinding.FragmentMovieDetailsBinding
import com.example.movielist.domain.dto.MovieDetailsEntity
import com.example.movielist.moviedetails.presentation.viewmodel.MovieDetailsViewModel
import com.example.movielist.utils.Constants.EMPTY
import com.example.movielist.utils.Constants.SPACE
import com.example.movielist.utils.loadImage
import com.example.movielist.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val viewModel: MovieDetailsViewModel by viewModel()

    private lateinit var movieDetails: MovieDetailsEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupObservers()
        movieDetails = args.movieDetails
        renderMovieDetails()
        handleFavoriteIconClick()
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.details_screen)
    }

    private fun setupObservers() {
        observe(viewModel.isInsertedSuccessfully, ::getInsertResult)
        observe(viewModel.isDeletedSuccessfully, ::getDeleteResult)
    }

    private fun getInsertResult(isSuccess: Boolean?) {
        isSuccess?.let {
            if (isSuccess == true) {
                Toast.makeText(context, getString(R.string.inserted_successfully), Toast.LENGTH_SHORT).show()
                binding?.imgFavorite?.setImageResource(R.drawable.fill_heart)
                movieDetails.isFavorite = true

            } else {
                Toast.makeText(context, getString(R.string.insertion_failed), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDeleteResult(isSuccess: Boolean?) {
        isSuccess?.let {
            if (isSuccess == true) {
                Toast.makeText(context, getString(R.string.deleted_successfully), Toast.LENGTH_SHORT).show()
                binding?.imgFavorite?.setImageResource(R.drawable.empty_heart)
                movieDetails.isFavorite = false
            } else {
                Toast.makeText(context, getString(R.string.deletion_failed), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun renderMovieDetails() {
        with(binding) {
            this?.let {
                val voteAvg = String.format(Locale.US, "%.1f", movieDetails.voteAverage)
                imgPoster.loadImage(movieDetails.posterPath ?: EMPTY)
                txtRatingAndReleaseYear.text = voteAvg.plus(" | ").plus(SPACE).plus(movieDetails.releaseDate)
                txtMovieLanguage.text = getString(R.string.language).plus(SPACE).plus(movieDetails.originalLanguage)
                txtMovieTitle.text = movieDetails.title
                txtMovieOverview.text = movieDetails.overview
                txtMovieVoteAverage.text = getString(R.string.vote_average).plus(SPACE).plus(voteAvg)
                txtMovieVoteCount.text = getString(R.string.vote_count).plus(SPACE).plus(movieDetails.voteCount)
                imgFavorite.setImageResource(
                    if (movieDetails.isFavorite) R.drawable.fill_heart
                    else R.drawable.empty_heart
                )
            }
        }
    }

    private fun handleFavoriteIconClick() {
        binding?.imgFavorite?.setOnClickListener {
            if (movieDetails.isFavorite) {
                viewModel.removeMovie(movieDetails)
            } else {
                viewModel.insertMovie(movieDetails)
            }
        }
    }
}