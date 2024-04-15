package com.ravi.imageloadingappdemo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.ravi.locodemo.model.Movie
import com.ravi.locodemo.model.SearchData
import com.ravi.locodemo.util.Constants.Companion.API_KEY
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
     val api: RetrofitApi
    ) {
    suspend fun getMovies(searchKey:String,page:Int): Response<SearchData> {
        return api.getSearchResult(API_KEY,searchKey,page)
    }
}