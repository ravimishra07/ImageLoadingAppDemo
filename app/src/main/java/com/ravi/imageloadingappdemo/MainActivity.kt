package com.ravi.imageloadingappdemo

import android.graphics.Bitmap
import android.os.Bundle
import android.util.LruCache
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.ravi.imageloadingappdemo.adapter.ImageAdapter
import com.ravi.imageloadingappdemo.adapter.LoaderStateAdapter
import com.ravi.imageloadingappdemo.databinding.ActivityMainBinding
import com.ravi.imageloadingappdemo.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { memoryCache?.let { ImageAdapter(it) } }
    private var memoryCache: LruCache<String, Bitmap>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initCache()
        setupRecyclerView()
        getImageData()
    }

    private fun getImageData() {
        lifecycleScope.launch {
            mainViewModel.fetchImages().collectLatest {
                mAdapter?.submitData(it)
            }
        }
    }

    private fun setupRecyclerView() {

        binding.rvMovies.adapter = mAdapter?.withLoadStateHeaderAndFooter(
            header = LoaderStateAdapter(),
            footer = LoaderStateAdapter()
        )
        mAdapter?.addLoadStateListener { loadState ->
            binding.rvMovies.isVisible = loadState.refresh is LoadState.NotLoading
            binding.mainProgressbar.isVisible = loadState.refresh is LoadState.Loading
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    this,
                    " Error:  ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.rvMovies.layoutManager = GridLayoutManager(this, 1)
        showLoader()
    }

    private fun showLoader() {
        binding.mainProgressbar.visibility = View.VISIBLE
    }

    fun initCache() {
        val maxMemory: Int = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8
        memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String?, bitmap: Bitmap) = bitmap.byteCount / 1024
        }
    }
}

