package com.movieapp.ui.profileFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.movieapp.R
import com.movieapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        setupHeader()

        return binding!!.root
    }

    private fun setupHeader() {
        requireActivity().findViewById<LinearLayout>(R.id.header).visibility = View.VISIBLE
        requireActivity().findViewById<TextView>(R.id.fragment_title).text =
            getString(R.string.profile_fragment_header)
    }
}