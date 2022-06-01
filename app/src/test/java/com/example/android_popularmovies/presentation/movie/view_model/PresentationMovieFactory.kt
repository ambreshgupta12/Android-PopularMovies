package com.example.android_popularmovies.presentation.movie.view_model

import com.example.android_popularmovies.data.source.remote.model.Movie

object PresentationMovieFactory {

    fun generateListOfMovies(size: Int): List<Movie> {
        val listOfMovies = mutableListOf<Movie>()
        repeat(size) {
            listOfMovies.add(generateMovie())
        }
        return listOfMovies
    }


    fun generateMovie(): Movie {
        return Movie(
            adult = DataFactory.getRandomBoolean(),
            id = DataFactory.getRandomInt(),
            title = DataFactory.getRandomString(),
            voteAverage = DataFactory.getRandomFloat(),
            posterPath = DataFactory.getRandomString(),
            popularity = DataFactory.getRandomFloat(),
            backdropPath = DataFactory.getRandomString(),
            originalLanguage = DataFactory.getRandomString(),
            originalTitle = DataFactory.getRandomString(),
            overview = DataFactory.getRandomString(),
            releaseDate = DataFactory.getRandomString(),
            video = DataFactory.getRandomBoolean(),
            voteCount = DataFactory.getRandomInt()
        )
    }
}
