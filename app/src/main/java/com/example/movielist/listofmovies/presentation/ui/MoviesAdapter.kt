package com.example.movielist.listofmovies.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.databinding.RowMovieBinding
import com.example.movielist.listofmovies.data.dto.Movie
import com.example.movielist.utils.Constants.EMPTY
import com.example.movielist.utils.loadImage

class MoviesAdapter(
    private val itemClickListener: (Movie) -> Unit,
    private val addToFavorite: (Movie) -> Unit
): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

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
        holder.bind(moviesList[position], itemClickListener, addToFavorite)
    }

    fun setMovies(moviesList: List<Movie>) {
        this.moviesList.clear()
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: RowMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, itemClickListener: (Movie) -> Unit, addToFavorite: (Movie) -> Unit) {
            binding.txtMovieName.text = movie.title
            binding.txtRating.text = String.format("%.1f", movie.voteAverage)
            binding.txtReleaseDate.text = movie.releaseDate
            binding.imgMoviePoster.loadImage(movie.posterPath ?: EMPTY)

            itemView.setOnClickListener {
                itemClickListener(movie)
            }

            binding.imgFavorite.setOnClickListener {
                addToFavorite(movie)
            }
        }
    }
}