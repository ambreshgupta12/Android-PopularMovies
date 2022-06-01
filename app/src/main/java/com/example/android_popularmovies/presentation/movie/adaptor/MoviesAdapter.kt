package com.example.android_popularmovies.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_popularmovies.databinding.MovieViewBinding
import com.example.android_popularmovies.data.source.remote.model.Movie


class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: MovieViewBinding =
            MovieViewBinding.inflate(layoutInflater, parent, false)
        return MoviesViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        return holder.bind(movies[position])
    }
}

class MoviesViewHolder(private val binding: MovieViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movie = movie
        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(binding.moviePhoto)
    }
}