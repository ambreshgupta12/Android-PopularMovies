package com.example.android_popularmovies

import com.example.android_popularmovies.data.source.remote.model.Movie

object MockMovies {

    fun generateListOfMovies(size: Int): List<Movie> {
        val listOfMovies = mutableListOf<Movie>()
        repeat(size) {
            listOfMovies.add(generateMovie())
        }
        return listOfMovies
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
