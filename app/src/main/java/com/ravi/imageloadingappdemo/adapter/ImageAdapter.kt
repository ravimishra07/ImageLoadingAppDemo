package com.ravi.imageloadingappdemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravi.imageloadingappdemo.databinding.MoviesRowLayoutBinding
import com.ravi.imageloadingappdemo.model.ImageData


import com.ravi.imageloadingappdemo.util.Constants

class ImageAdapter : PagingDataAdapter<ImageData, RecyclerView.ViewHolder>(REPO_COMPARATOR) {
    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ImageData>() {
            override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData) =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData) =
                oldItem.url == newItem.url
        }
    }


    class ListViewHolder(private val binding: MoviesRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: ImageData) {
            Log.v("MoviesAdapter", movie.toString())
            binding.tvMovieTitle.text = movie.url
            binding.tvMovieType.text = movie.url
            binding.tvYear.text = movie.url
//            Glide.with(binding.root.context)
//                .load(movie.poster)
//                .placeholder(R.drawable.placeholder_img)
//                .into(binding.movieImage)
        }

        companion object {
            fun from(parent: ViewGroup): ListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MoviesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return ListViewHolder(binding)
            }
        }
    }
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      return ListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as ListViewHolder).bind(it)
        }
    }
}