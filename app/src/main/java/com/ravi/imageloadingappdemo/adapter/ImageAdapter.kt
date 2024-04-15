package com.ravi.imageloadingappdemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravi.imageloadingappdemo.databinding.MoviesRowLayoutBinding
import com.ravi.imageloadingappdemo.model.ImageData
import com.ravi.imageloadingappdemo.model.ImageDto


import com.ravi.imageloadingappdemo.util.Constants

class ImageAdapter : PagingDataAdapter<ImageDto, RecyclerView.ViewHolder>(REPO_COMPARATOR) {
    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ImageDto>() {
            override fun areItemsTheSame(oldItem: ImageDto, newItem: ImageDto) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ImageDto, newItem: ImageDto) =
                oldItem.id == newItem.id
        }
    }


    class ListViewHolder(private val binding: MoviesRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: ImageDto) {
            Log.v("MoviesAdapter", movie.toString())
            binding.tvMovieTitle.text = movie.altDescription
            binding.tvMovieType.text = movie.altDescription
            binding.tvYear.text = movie.blurHash
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