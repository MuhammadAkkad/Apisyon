package com.example.apisyon.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apisyon.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MovieViewModel by viewModels()

    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initAdapter()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.movies.observe(this) {
            adapter.submitData(this.lifecycle, it)
        }
    }

    private fun initAdapter() {
        adapter = MovieAdapter()
        binding.movieRv.layoutManager = LinearLayoutManager(this)
        binding.movieRv.adapter = adapter
    }
}