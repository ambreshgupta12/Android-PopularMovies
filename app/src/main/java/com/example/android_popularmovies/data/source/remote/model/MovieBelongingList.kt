package com.example.android_popularmovies.data.source.remote.model

data class MovieBelongingList(
    val id: Long,
    val page: Long,
    val results: List<MovieBelonging>,
    val totalPages: Long,
    val totalResults: Long
)

data class MovieBelonging(
    val description: String,
    val favoriteCount: Long,
    val id: Long,
    val itemCount: Long,
    val iso639_1: ISO639_1,
    val listType: ListType,
    val name: String,
    val posterPath: Any? = null
)

enum class ISO639_1 {
    En,
    Es,
    Pt
}

enum class ListType {
    Movie
}
