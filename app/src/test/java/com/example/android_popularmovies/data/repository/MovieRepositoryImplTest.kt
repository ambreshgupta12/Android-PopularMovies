package com.example.android_popularmovies.data.repository

import com.example.android_popularmovies.data.source.remote.MovieApiService
import com.example.android_popularmovies.data.source.remote.model.MovieDetailsModel
import com.example.android_popularmovies.data.source.remote.model.MovieListModel
import com.example.android_popularmovies.presentation.movie.view_model.MockMovies
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.createTestCoroutineScope
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = createTestCoroutineScope(testDispatcher)

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
        val mockMovieDetails = MockMovies.generateMovieDetails();
        val response = runBlocking {
            stubMoviesDetails(Response.success(mockMovieDetails))
            movieApiService.movieDetails(0)
        }
        assert(response.body()!!.title == mockMovieDetails.title)
    }


    private fun stubPopularMovies(single: Single<MovieListModel>) {
        Mockito.`when`(movieApiService.popularMovies()).thenReturn(
            single
        )
    }

    private suspend fun stubMoviesDetails(single: Response<MovieDetailsModel>) {
        Mockito.`when`(movieApiService.movieDetails(0)).thenReturn(
            single
        )
    }
}