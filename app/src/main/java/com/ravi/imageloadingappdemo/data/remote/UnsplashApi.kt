package com.ravi.imageloadingappdemo.data.remote

import com.ravi.imageloadingappdemo.model.ImageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("/photos/")
    suspend fun getUnsplashImages(
        @Query("client_id") accessKey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10,
    ): Response<List<ImageDto>>

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }
}