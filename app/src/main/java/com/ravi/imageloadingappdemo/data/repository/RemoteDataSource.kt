package com.ravi.imageloadingappdemo.data.repository

import android.media.Image
import com.ravi.imageloadingappdemo.data.remote.UnsplashApi
import com.ravi.imageloadingappdemo.model.ImageDto
import com.ravi.imageloadingappdemo.util.Constants.Companion.API_KEY
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
     val api: UnsplashApi
    ) {
    suspend fun getImages(page:Int): Response<List<ImageDto>> {
        return api.getUnsplashImages(API_KEY,page)
    }
}