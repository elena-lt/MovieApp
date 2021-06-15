package com.movieapp.ui.movieDetailsFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.paging.map
import com.bumptech.glide.Glide
import com.movieapp.R
import com.movieapp.adapters.LoadingStateAdapter

import com.movieapp.adapters.ReviewAdapter
import com.movieapp.adapters.SinglePosterAdapter
import com.movieapp.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding

    val args: MovieDetailsFragmentArgs by navArgs()

    private val viewModel: MovieDetailsViewModel by viewModels()

    @Inject
    lateinit var reviewsAdapter: ReviewAdapter

    @Inject
    lateinit var similarMoviesAdapter: SinglePosterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val view = binding!!.root

        setupRecycler()
        loadData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.movie = args.movie

        binding!!.btnPlay.setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailsFragment_to_trailerFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loadData() {
        val movieId = args.movie.id

        lifecycleScope.launch {
            viewModel.getReviews(movieId).collectLatest {
                reviewsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                Log.d(TAG, "Item count: ${reviewsAdapter.itemCount}")
            }
        }

        lifecycleScope.launch {
            viewModel.getSimilarMovies(movieId).collectLatest {
                similarMoviesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    private fun setupRecycler() {
        binding!!.rvReviews.adapter = reviewsAdapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter { reviewsAdapter.retry() },
            footer = LoadingStateAdapter { reviewsAdapter.retry() }
        )

        reviewsAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.NotLoading && reviewsAdapter.itemCount == 0) {
                showEmptyReviews(true)
            } else {
                showEmptyReviews(false)
            }
        }

        binding!!.rvSimilarMovies.adapter = similarMoviesAdapter
    }

    private fun showEmptyReviews(isListEmpty: Boolean) {
        with(binding!!) {
            txtNoReviews.isVisible = isListEmpty
            rvReviews.isVisible = !isListEmpty
        }
    }

    companion object {
        const val TAG = "DetailsFragment"
    }
}