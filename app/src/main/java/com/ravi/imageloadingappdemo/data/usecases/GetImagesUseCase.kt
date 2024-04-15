package com.ravi.imageloadingappdemo.data.usecases


import com.ravi.imageloadingappdemo.util.Resource
import com.ravi.imageloadingappdemo.data.UnsplashImageRepository
import com.ravi.imageloadingappdemo.model.ImageData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repository: UnsplashImageRepository
) {

    operator fun invoke(params: Param): Flow<Resource<List<ImageData>>> {
       return repository.getImages(params.accessKey, params.pageNum)
    }

    data class Param(
        val accessKey: String,
        val pageNum: Int
    )
}