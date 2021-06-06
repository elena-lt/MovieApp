package com.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.databinding.ItemMovieLargeposterBinding
import com.movieapp.models.Movie

class MovieAdapter (val onClickListener: (Movie) -> Unit) : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(DIFF_UTIL) {

    inner class MovieViewHolder(val binding: ItemMovieLargeposterBinding, val onClickListener: (Movie) -> Unit) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                onClickListener(getItem(bindingAdapterPosition)!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieLargeposterBinding.inflate(LayoutInflater.from(parent.context))){
            onClickListener(it)
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
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