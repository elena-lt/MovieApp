package com.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.databinding.ItemMovieSmallposterBinding
import com.movieapp.models.Movie

class MovieAdapter2 : PagingDataAdapter<Movie, MovieAdapter2.MovieSmallViewHolder>(DIFF_UTIL) {

    inner class MovieSmallViewHolder(val binding: ItemMovieSmallposterBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter2.MovieSmallViewHolder {
        return MovieSmallViewHolder(ItemMovieSmallposterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieAdapter2.MovieSmallViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            movie = item
            executePendingBindings()
        }
    }
    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.hashCode() == newItem.hashCode()
        }
    }

}