package com.example.apisyon.ui.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private lateinit var _binding: FragmentMovieListBinding
    private val binding get() = _binding

    private val viewModel: MovieListViewModel by viewModels()

    private lateinit var adapter: MovieAdapter

    lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        initAdapter()
        subscribeObservers()
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         navController = Navigation.findNavController(view)

    }

    private fun subscribeObservers() {
        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitData(this.lifecycle, it)
        }
    }

    private fun initAdapter() {
        adapter = MovieAdapter {
            navigateToMovieDetail(it)
        }
        binding.movieRv.layoutManager = LinearLayoutManager(requireContext())
        binding.movieRv.adapter = adapter
    }

    private fun navigateToMovieDetail(selectedMovie: MovieModel) {
        val action =
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(selectedMovie)
        navController.navigate(action)
    }
}