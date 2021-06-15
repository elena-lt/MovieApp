package com.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.databinding.ItemReviewBinding
import com.movieapp.models.Review
import javax.inject.Inject

class ReviewAdapter @Inject constructor() : PagingDataAdapter<Review, ReviewAdapter.ReviewViewHolder>(COMPATATOR) {

    inner class ReviewViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val currItem = getItem(position)
        holder.binding.review = currItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object {
        private val COMPATATOR = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
                oldItem.hashCode() == newItem.hashCode()
        }
    }
}