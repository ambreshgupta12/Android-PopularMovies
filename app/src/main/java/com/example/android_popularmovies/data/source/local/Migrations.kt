package com.example.android_popularmovies.data.source.local

import androidx.room.migration.Migration

class Migrations {
    companion object {
        const val DB_VERSION = 1

        fun getMigrations(): Array<Migration> {
            return arrayOf()
        }
    }
}
