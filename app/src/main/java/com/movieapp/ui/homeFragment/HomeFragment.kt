package com.movieapp.ui.homeFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.movieapp.adapters.MovieAdapter
import com.movieapp.adapters.MovieAdapter2
import com.movieapp.databinding.FragmentHomeBinding
import com.movieapp.models.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var adapter: MovieAdapter
    private lateinit var adapterSmall: MovieAdapter2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding!!.root

        setUpRecycler()
        loadInitialData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        adapter.addLoadStateListener { state ->
//            with(viewBinding) {
//                news.isVisible = state.refresh != LoadState.Loading
//                progress.isVisible = state.refresh == LoadState.Loading
//            }
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loadInitialData() {
        lifecycleScope.launch {
            homeViewModel.getUpcomingMovies().collectLatest {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        lifecycleScope.launch {
            homeViewModel.getTopRatedMovies().collectLatest {
                adapterSmall.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    private fun setUpRecycler() {
        adapter = MovieAdapter {
            goToMovieDetails(it)
        }
        adapterSmall = MovieAdapter2()
        binding?.rvTrendingMovies?.adapter = adapter
        binding?.rvTopRatedMovies?.adapter = adapterSmall
    }

    private fun goToMovieDetails(it: Movie) {
        Log.d(TAG, "Movie passed: ${it.title}, ${it.poster_path}")
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(it)
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}