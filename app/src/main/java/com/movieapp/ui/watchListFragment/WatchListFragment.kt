package com.movieapp.ui.watchListFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.movieapp.R
import com.movieapp.databinding.FragmentWatchListBinding


class WatchListFragment : Fragment() {

    private lateinit var _binding: FragmentWatchListBinding
    private val binding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatchListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}