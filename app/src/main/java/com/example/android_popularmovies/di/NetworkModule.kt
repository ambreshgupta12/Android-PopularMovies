package com.example.android_popularmovies.di

import android.content.Context
import com.example.android_popularmovies.R
import com.example.android_popularmovies.data.repository.MovieRepositoryImpl
import com.example.android_popularmovies.data.source.local.MovieDao
import com.example.android_popularmovies.data.source.remote.MovieApiService
import com.example.android_popularmovies.domain.repository.MovieRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private const val API_BASE_URL = "https://api.themoviedb.org/3/"
    val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val interceptor = Interceptor { chain ->
            val url =
                chain.request().url.newBuilder().addQueryParameter(
                    "api_key", context.resources.getString(
                        R.string.api_key
                    )
                )
                    .build()
            val request = chain.request().newBuilder().url(url).build()

            return@Interceptor chain.proceed(request)

        }
        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }


    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): MovieApiService {

        return retrofit.create(MovieApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        retrofitService: MovieApiService,
        movieDao: MovieDao
    ): MovieRepository {
        return MovieRepositoryImpl(retrofitService,movieDao)
    }

}

