package com.ravi.imageloadingappdemo.data

import com.ravi.imageloadingappdemo.model.ImageData
import com.ravi.imageloadingappdemo.util.Resource
import kotlinx.coroutines.flow.Flow

interface UnsplashImageRepository {
    fun getImages(accessKey: String, pageNum: Int): Flow<Resource<List<ImageData>>>
}