package com.movieapp.ui.trailerFragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.movieapp.BuildConfig
import com.movieapp.R
import com.movieapp.databinding.FragmentTrailerBinding

class TrailerFragment : Fragment() {

    private var _binding: FragmentTrailerBinding? = null
    private val binding get() = _binding

    private var YPlayer: YouTubePlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrailerBinding.inflate(inflater, container, false)
        val view = _binding!!.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initYouTube()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun initYouTube() {
        val youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance()
        val trasaction = childFragmentManager.beginTransaction()
        trasaction.replace(R.id.youtube_player, youTubePlayerFragment).commit()

        youTubePlayerFragment.initialize(BuildConfig.YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
                if (!p2) {
                    Log.d(TAG, "Initialized")
                    YPlayer = p1
                    YPlayer!!.setFullscreen(false)
                    YPlayer!!.loadVideo("2zNSgSzhBfM")
                    YPlayer!!.play()
                }
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Log.d(TAG, "Something went wrong")
            }

        })

    }

    companion object {
        const val TAG = "TrailerFragment"
    }
}