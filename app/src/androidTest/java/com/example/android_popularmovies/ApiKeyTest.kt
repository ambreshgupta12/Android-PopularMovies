package com.example.android_popularmovies

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ApiKeyTest {

    @Test
    fun checkAPIKeyExists() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        assert(context.getString(R.string.api_key).isNotEmpty())
    }
}
