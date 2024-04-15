package com.ravi.imageloadingappdemo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ravi.imageloadingappdemo.model.MainData
import com.ravi.imageloadingappdemo.util.NetworkResult
import com.ravi.locodemo.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var imageResponse: MutableLiveData<NetworkResult<MainData>> = MutableLiveData()

    fun getMovies(query: String) = viewModelScope.launch {
        getMoviesSafeCall(query, Repository.DEFAULT_PAGE_INDEX)
    }

    @ExperimentalPagingApi
    fun fetchImages(): Flow<PagingData<MainData>> {
        return repository.loadImageFlow().cachedIn(viewModelScope)
    }

    private suspend fun getMoviesSafeCall(query: String, page: Int) {
        imageResponse.value = NetworkResult.Loading()
        try {
            val response = repository.remote.getMovies(query, page)
            imageResponse.value = handleImageResponse(response)

        } catch (e: Exception) {
            imageResponse.value = NetworkResult.Error("Movies not found." + e.localizedMessage)
        }

    }

    private fun handleImageResponse(response: Response<MainData>): NetworkResult<MainData> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }

            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }

            response.body()?.list.isNullOrEmpty() -> {
                return NetworkResult.Error("No not found.")
            }
//
            response.isSuccessful -> {
                val questions = response.body()
                return NetworkResult.Success(questions!!)
            }

            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }


}

