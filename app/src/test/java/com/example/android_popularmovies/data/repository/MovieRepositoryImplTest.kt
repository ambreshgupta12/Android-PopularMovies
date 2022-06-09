package com.example.android_popularmovies.data.repository

import com.example.android_popularmovies.data.source.remote.MovieApiService
import com.example.android_popularmovies.data.source.remote.model.Movie
import com.example.android_popularmovies.data.source.remote.model.MovieBelongingList
import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import com.example.android_popularmovies.presentation.movie.view_model.MockMovies
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
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
    fun testLoadMovies_returnData() {
        stubPopularMovies(Single.just(MockMovies.generateMovieListModel(6)))

        val testObserver = movieApiService.popularMovies().test()

        assert(testObserver.values()[0].results!!.size == 6)
    }

    @Test
    fun testGetMovieDetails_returnData() {
        val mockMovieDetails = MockMovies.generateMovie()
        val response = runBlocking {
            stubMoviesDetails(Response.success(mockMovieDetails))
            movieApiService.movieDetails(0)
        }
        assert(response.body()!!.title == mockMovieDetails.title)
    }

    @Test
    fun testGetMovieBelongings_returnData() {
        val mockMovieBelongings = MockMovies.generateMovieBelongingList(10)
        val response = runBlocking {
            stubMoviesBelongings(Response.success(mockMovieBelongings))
            movieApiService.movieBelongings(0)
        }
        assert(response.body()!!.results.size == mockMovieBelongings.results.size)
    }

    private fun stubPopularMovies(single: Single<MovieListModel>) {
        Mockito.`when`(movieApiService.popularMovies()).thenReturn(
            single
        )
    }

    private suspend fun stubMoviesDetails(single: Response<Movie>) {
        Mockito.`when`(movieApiService.movieDetails(0)).thenReturn(
            single
        )
    }

    private suspend fun stubMoviesBelongings(movieBelonging: Response<MovieBelongingList>) {
        Mockito.`when`(movieApiService.movieBelongings(0)).thenReturn(
            movieBelonging
        )
    }
}
