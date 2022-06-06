package com.example.android_popularmovies.data.source

interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E
}
