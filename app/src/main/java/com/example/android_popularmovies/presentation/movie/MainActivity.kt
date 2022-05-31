package com.example.android_popularmovies.presentation.movie

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_popularmovies.R
import com.example.android_popularmovies.databinding.ActivityMainBinding
import com.example.android_popularmovies.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel.movieData.observe(this) {
            if (it != null) {
                setRecyclerView(it)
            }

        }
    }

    private fun setRecyclerView(list: List<Movie>) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = MoviesAdapter(list)
        }
    }


}