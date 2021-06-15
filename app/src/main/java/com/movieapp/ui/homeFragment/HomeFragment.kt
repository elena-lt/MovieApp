package com.movieapp.ui.homeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.movieapp.adapters.LoadingStateAdapter
import com.movieapp.adapters.MovieAdapter
import com.movieapp.adapters.MovieAdapter2
import com.movieapp.databinding.FragmentHomeBinding
import com.movieapp.models.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

        binding!!.btnRetry.setOnClickListener { adapter.retry() }
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
        adapter = MovieAdapter { goToMovieDetails(it) }

        binding?.rvTrendingMovies?.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter { adapter.retry() },
            footer = LoadingStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            with(binding!!) {
                rvTrendingMovies.isVisible = loadState.source.refresh is LoadState.NotLoading
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                errorMsg.isVisible = loadState.source.refresh is LoadState.Error
                errorMsg.text = (loadState.refresh as? LoadState.Error)?.error?.localizedMessage
            }
        }

        adapterSmall = MovieAdapter2()
        binding?.rvTopRatedMovies?.adapter = adapterSmall
    }


    private fun goToMovieDetails(it: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(it)
        findNavController().navigate(action)
    }

}