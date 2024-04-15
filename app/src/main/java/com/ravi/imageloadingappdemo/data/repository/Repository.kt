package com.ravi.imageloadingappdemo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ravi.imageloadingappdemo.data.ImagePagingSource
import com.ravi.imageloadingappdemo.model.ImageDto
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {
    val remote = remoteDataSource
    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
    }
    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

    @ExperimentalPagingApi
    fun loadImageFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<ImageDto>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { ImagePagingSource(remote.api) }
        ).flow
    }
}
