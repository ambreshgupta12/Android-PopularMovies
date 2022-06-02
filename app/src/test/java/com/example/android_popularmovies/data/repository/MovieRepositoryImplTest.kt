package com.example.android_popularmovies.data.repository

import com.example.android_popularmovies.data.source.remote.MovieApiService
import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel
import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import com.example.android_popularmovies.presentation.movie.view_model.MockMovies
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {

    @Mock
    private lateinit var movieApiService: MovieApiService


    @Test
    fun getPopularMovies_Completes() {
        stubPopularMovies(Single.just(MockMovies.generateMovieListModel(6)))

        val testObserver = movieApiService.popularMovies().test()

        testObserver.assertComplete()
    }

    @Test
    fun testLoadMovies() {
        stubPopularMovies(Single.just(MockMovies.generateMovieListModel(6)))

        val testObserver = movieApiService.popularMovies().test()

        assert(testObserver.values()[0].results!!.size == 6)
    }

    @Test
    fun testGetMovieDetails() {
        val mockMovieDetails = MockMovies.generateMovieDetails();

        stubMoviesDetails(Single.just(mockMovieDetails))

        val testObserver = movieApiService.movieDetails(0).test()

        assert(testObserver.values()[0].title == mockMovieDetails.title)
    }


    private fun stubPopularMovies(single: Single<MovieListModel>) {
        Mockito.`when`(movieApiService.popularMovies()).thenReturn(
            single
        )
    }

    private fun stubMoviesDetails(single: Single<MovieDetailsModel>) {
        Mockito.`when`(movieApiService.movieDetails(0)).thenReturn(
            single
        )
    }
}