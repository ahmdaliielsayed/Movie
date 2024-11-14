package com.example.movielist.moviedetails.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.movielist.R
import com.example.movielist.databinding.FragmentMovieDetailsBinding
import com.example.movielist.moviedetails.domain.MovieDetails
import com.example.movielist.utils.Constants.EMPTY
import com.example.movielist.utils.Constants.SPACE
import com.example.movielist.utils.loadImage
import java.util.Locale

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding

    private val args: MovieDetailsFragmentArgs by navArgs()

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
        val movieDetails = args.movieDetails
        renderMovieDetails(movieDetails)
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.details_screen)
    }

    private fun renderMovieDetails(movieDetails: MovieDetails) {
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
            }
        }
    }
}