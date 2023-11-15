package com.example.apisyon.ui.movieList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apisyon.databinding.FragmentMovieListBinding
import com.example.apisyon.ui.model.MovieModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null

    private val binding get() = _binding

    private val viewModel: MovieListViewModel by viewModels()

    private lateinit var adapter: MovieAdapter

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        initAdapter()
        subscribeObservers()
        initClickListeners()
        return binding?.root
    }

    private fun initAdapter() {
        adapter = MovieAdapter {
            navigateToMovieDetail(it)
        }
        binding?.movieRv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.movieRv?.adapter = adapter
    }

    private fun subscribeObservers() {
        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitData(this.lifecycle, it)
        }
    }

    private fun navigateToMovieDetail(selectedMovie: MovieModel) {
        val action =
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(selectedMovie)
        navController.navigate(action)
    }

    private fun initClickListeners() {
        binding?.layoutButtonAdd?.addBtn?.setOnClickListener {
            Log.w("Button Add Click:", "implementation not specified in documentation")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}