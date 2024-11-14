package com.example.movielist.listofmovies.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.databinding.RowMovieBinding
import com.example.movielist.domain.dto.Movie
import com.example.movielist.utils.Constants.EMPTY
import com.example.movielist.utils.loadImage
import java.util.Locale

class MoviesAdapter(
    private val itemClickListener: (Movie) -> Unit,
    private val addToFavorite: (Movie) -> Unit,
    private val removeFromFavorite: (Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var moviesList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList[position], itemClickListener, addToFavorite, removeFromFavorite)
    }

    fun setMovies(moviesList: List<Movie>) {
        this.moviesList.clear()
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: RowMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movie: Movie,
            itemClickListener: (Movie) -> Unit,
            addToFavorite: (Movie) -> Unit,
            removeFromFavorite: (Movie) -> Unit
        ) {
            binding.txtMovieName.text = movie.title
            binding.txtRating.text = String.format(Locale.US, "%.1f", movie.voteAverage)
            binding.txtReleaseDate.text = movie.releaseDate
            binding.imgMoviePoster.loadImage(movie.posterPath ?: EMPTY)
            binding.imgFavorite.setImageResource(
                if (movie.isFavorite) R.drawable.fill_heart
                else R.drawable.empty_heart
            )

            itemView.setOnClickListener {
                itemClickListener(movie)
            }

            binding.imgFavorite.setOnClickListener {
                if (movie.isFavorite) {
                    removeFromFavorite(movie)
                } else {
                    addToFavorite(movie)
                }
            }
        }
    }
}