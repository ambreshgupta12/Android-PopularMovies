package com.example.android_popularmovies.data.source.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MovieListModel {
    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<Movie>? = null

    override fun toString(): String {
        return "MovieResponse(page=$page, totalResults=$totalResults, totalPages=$totalPages, results=$results)"
    }
}

@Entity(tableName = "movies")
class Movie {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("popularity")
    @Expose
    var popularity: Float? = null

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

    @SerializedName("video")
    @Expose
    var video: Boolean? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

//    @SerializedName("genre_ids")
//    @Expose
//    var genreIds: List<Int>? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    override fun toString(): String {
        return "Movie(id=$id, popularity=$popularity, voteCount=$voteCount, video=$video, posterPath=$posterPath, adult=$adult, backdropPath=$backdropPath, originalLanguage=$originalLanguage, originalTitle=$originalTitle, title=$title, voteAverage=$voteAverage, overview=$overview, releaseDate=$releaseDate)"
    }

}