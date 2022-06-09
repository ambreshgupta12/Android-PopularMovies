package com.example.android_popularmovies.utils

interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E
}
