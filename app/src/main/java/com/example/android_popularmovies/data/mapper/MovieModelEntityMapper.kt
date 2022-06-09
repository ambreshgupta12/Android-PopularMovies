package com.example.android_popularmovies.data

import com.example.android_popularmovies.data.source.local.model.MovieEntity
import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.utils.EntityMapper
import javax.inject.Inject

class MovieToMovieEntityMapper @Inject constructor() :
    EntityMapper<Movie, MovieEntity> {
    override fun mapFromModel(model: Movie): MovieEntity {
        return MovieEntity(
            model.id!!,
            model.posterPath!!,
            model.backdropPath!!,
            model.title!!,
            model.voteAverage!!,
            model.overview!!,
        )
    }
}

class MovieEntityToMovieMapper @Inject constructor() :
    EntityMapper<MovieEntity, Movie> {
    override fun mapFromModel(model: MovieEntity): Movie {
        return Movie(
            id = model.id,
            posterPath = model.posterPath,
            backdropPath = model.backdropPath,
            title = model.title,
            voteAverage = model.voteAverage,
            overview = model.overview,
        )
    }
}
