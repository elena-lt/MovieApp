package com.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.databinding.ItemMovieSinglePosterBinding
import com.movieapp.databinding.ItemMovieSmallposterBinding
import com.movieapp.models.Movie
import javax.inject.Inject

class SinglePosterAdapter @Inject constructor(): PagingDataAdapter<Movie, SinglePosterAdapter.ImageViewHolder>(DIFF_UTIL) {

    inner class ImageViewHolder(val binding: ItemMovieSinglePosterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            movie = item
//            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemMovieSinglePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}