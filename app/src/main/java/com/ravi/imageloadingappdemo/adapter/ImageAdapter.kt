package com.ravi.imageloadingappdemo.adapter


import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravi.imageloadingappdemo.R
import com.ravi.imageloadingappdemo.databinding.MoviesRowGridLayoutBinding
import com.ravi.imageloadingappdemo.model.ImageDto
import com.ravi.imageloadingappdemo.util.ProcessImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ImageAdapter(val memoryCache: LruCache<String, Bitmap>) :
    PagingDataAdapter<ImageDto, RecyclerView.ViewHolder>(REPO_COMPARATOR) {
    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ImageDto>() {
            override fun areItemsTheSame(oldItem: ImageDto, newItem: ImageDto) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ImageDto, newItem: ImageDto) =
                oldItem.id == newItem.id
        }
    }

    inner class ListViewHolder(private val binding: MoviesRowGridLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageModel: ImageDto) {
            Log.v("MoviesAdapter", imageModel.toString())
           val text = if(imageModel.description.isNullOrEmpty()){
                imageModel.altDescription
            }else{
                imageModel.description
            }
            binding.title.text = text
            imageModel.urls.regular?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    val processImageObject = imageModel.urls.small?.let { it1 -> ProcessImage(it1) }

                    val userBitmap = processImageObject?.getBitmapFromURL(
                        processImageObject.imageUrl,
                        memoryCache,
                        layoutPosition
                    )
                    withContext(Dispatchers.Main){
                        binding.ivImage.setImageBitmap(userBitmap)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = MoviesRowGridLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as ListViewHolder).bind(it)
        }
    }
}