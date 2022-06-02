package com.example.android_popularmovies.presentation.movie.view_model

import RandomDataFactory
import com.example.android_popularmovies.data.source.remote.model.BelongsToCollection
import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel
import com.example.android_popularmovies.data.source.remote.model.MovieListModel

object MockMovies {

    fun generateListOfMovies(size: Int): List<Movie> {
        val listOfMovies = mutableListOf<Movie>()
        repeat(size) {
            listOfMovies.add(generateMovie())
        }
        return listOfMovies
    }

    fun generateMovieListModel(size: Int): MovieListModel {
        val listOfMovies = mutableListOf<Movie>()
        repeat(size) {
            listOfMovies.add(generateMovie())
        }

        val model = MovieListModel();
        model.results = listOfMovies;
        model.page = RandomDataFactory.getRandomInt()
        model.totalResults = RandomDataFactory.getRandomInt()
        return model;
    }

    fun generateMovieDetails(): MovieDetailsModel {
        return MovieDetailsModel(
            adult = RandomDataFactory.getRandomBoolean(),
            id = RandomDataFactory.getRandomLong(),
            title = RandomDataFactory.getRandomString(),
            voteAverage = RandomDataFactory.getRandomDouble(),
            posterPath = RandomDataFactory.getRandomString(),
            popularity = RandomDataFactory.getRandomDouble(),
            backdropPath = RandomDataFactory.getRandomString(),
            originalLanguage = RandomDataFactory.getRandomString(),
            originalTitle = RandomDataFactory.getRandomString(),
            overview = RandomDataFactory.getRandomString(),
            releaseDate = RandomDataFactory.getRandomString(),
            video = RandomDataFactory.getRandomBoolean(),
            voteCount = RandomDataFactory.getRandomLong(),
            belongsToCollection = BelongsToCollection(
                id = RandomDataFactory.getRandomLong(),
                name = RandomDataFactory.getRandomString(),
                posterPath = RandomDataFactory.getRandomString(),
                backdropPath = RandomDataFactory.getRandomString()
            ),

            budget = RandomDataFactory.getRandomLong(),
            genres = listOf(),
            homepage = RandomDataFactory.getRandomString(),
            imdbID = RandomDataFactory.getRandomString(),
            productionCompanies = listOf(),
            productionCountries = listOf(),
            revenue = RandomDataFactory.getRandomLong(),
            runtime = RandomDataFactory.getRandomLong(),
            spokenLanguages = listOf(),
            status = RandomDataFactory.getRandomString(),
            tagline = RandomDataFactory.getRandomString(),
        );
    }


    private fun generateMovie(): Movie {
        return Movie(
            adult = RandomDataFactory.getRandomBoolean(),
            id = RandomDataFactory.getRandomInt(),
            title = RandomDataFactory.getRandomString(),
            voteAverage = RandomDataFactory.getRandomFloat(),
            posterPath = RandomDataFactory.getRandomString(),
            popularity = RandomDataFactory.getRandomFloat(),
            backdropPath = RandomDataFactory.getRandomString(),
            originalLanguage = RandomDataFactory.getRandomString(),
            originalTitle = RandomDataFactory.getRandomString(),
            overview = RandomDataFactory.getRandomString(),
            releaseDate = RandomDataFactory.getRandomString(),
            video = RandomDataFactory.getRandomBoolean(),
            voteCount = RandomDataFactory.getRandomInt()
        )
    }
}
