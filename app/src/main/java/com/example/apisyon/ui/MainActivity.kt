package com.example.apisyon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apisyon.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)
    }

    override fun onDestroy() {
        super.onDestroy()
        // prevent memory leak.
        _binding = null
    }

}