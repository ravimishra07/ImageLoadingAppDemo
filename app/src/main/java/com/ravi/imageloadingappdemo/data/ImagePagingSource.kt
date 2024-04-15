package com.ravi.imageloadingappdemo.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ravi.imageloadingappdemo.data.remote.UnsplashApi
import com.ravi.imageloadingappdemo.data.repository.Repository.Companion.DEFAULT_PAGE_INDEX
import com.ravi.imageloadingappdemo.model.ImageDto
import com.ravi.imageloadingappdemo.util.Constants
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class ImagePagingSource@Inject constructor(
    private val apiService: UnsplashApi):
    PagingSource<Int, ImageDto>() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageDto> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = apiService.getUnsplashImages(Constants.API_KEY, page, 10)

                LoadResult.Page(
                    response.body()?: emptyList(), prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                    nextKey = if (response.body()?.isEmpty() == true) null else page + 1
                )
            if(response.body()?.isNotEmpty() == true){
                LoadResult.Page(
                    response.body()?: emptyList(), prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                    nextKey = if (response.body()?.isEmpty()==true) null else page + 1
                )
            }else{
                LoadResult.Error(Throwable("no data found"))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageDto>): Int? {
        return null
    }

}