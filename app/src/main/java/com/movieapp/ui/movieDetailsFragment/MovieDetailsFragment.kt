package com.movieapp.ui.movieDetailsFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.movieapp.R
import com.movieapp.adapters.LoadingStateAdapter

import com.movieapp.adapters.ReviewAdapter
import com.movieapp.databinding.FragmentMovieDetailsBinding
import com.movieapp.models.Review
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding

    val args: MovieDetailsFragmentArgs by navArgs()

    private val viewModel: MovieDetailsViewModel by viewModels()

    private lateinit var reviewsAdapter: ReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val view = binding!!.root

        setupRecycler()
        getReview()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.movie = args.movie

        subscribeToObservers()

        binding!!.btnPlay.setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailsFragment_to_trailerFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getReview() {
        val movieId = args.movie.id

        lifecycleScope.launch {
            viewModel.getReviews(movieId).collectLatest(reviewsAdapter::submitData)
        }
    }

    private fun subscribeToObservers() {
//        viewModel.error.observe(viewLifecycleOwner, {
//            Toast.makeText(requireContext(), "Cannot load reviews, $it", Toast.LENGTH_SHORT).show()
//        })

        viewModel.noReviewsFound.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "No reviews found for this movie", Toast.LENGTH_SHORT).show()
        })

        viewModel.reviews.observe(viewLifecycleOwner, {
            binding?.firstVisibleReview?.tvAuthorName!!.text = it.results?.get(0)!!.author
            binding?.firstVisibleReview?.tvReviewContent!!.text = it.results[0].content
            val uri = getString(R.string.image_base_url_w200) + "${it.results[0].author_details!!.avatar_path}"
            Glide.with(requireContext()).load(uri.toUri()).centerCrop().into(binding!!.firstVisibleReview.imgAuthor)
        })

    }

    private fun setupRecycler() {
        reviewsAdapter = ReviewAdapter()
        binding!!.rvReviews.adapter = reviewsAdapter
            .withLoadStateHeaderAndFooter(
                header = LoadingStateAdapter(),
                footer = LoadingStateAdapter()
            )
    }

    companion object {
        const val TAG = "MovieDetailsFragment"
    }
}