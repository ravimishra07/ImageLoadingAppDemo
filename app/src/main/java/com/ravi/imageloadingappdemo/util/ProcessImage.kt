package com.ravi.imageloadingappdemo.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

// This class is responsible for processing an image, converting from url to bitmap and storing
// it to the local cache
class ProcessImage(var imageUrl: String) {
    var key: Int = 0

    // This function takes image url and converts it to bitmap, after that it stores it to the cache
    // in key-value pair format, with a key being the item position in recyclerView
    fun getBitmapFromURL(
        src: String?,
        memoryCache: LruCache<String, Bitmap>,
        key: Int
    ): Bitmap? {
        try {
            val cacheImage = CacheImages(memoryCache, key)
            // Retrieves the cache by providing the key value.
            var myBitmap = cacheImage.getBitmapFromMemCache(cacheImage.getKey().toString())

            if (myBitmap != null) {
                Log.d("cached", "Got the cached content")
                // if the cache is not null, return the cache content
                return myBitmap
            } else {
                Log.d("cached", "Got new content")
                val url = URL(src)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                myBitmap = BitmapFactory.decodeStream(input)
                cacheImage.addBitmapToMemoryCache(cacheImage.getKey().toString(), myBitmap)

                return myBitmap
            }
        } catch (e: IOException) {
            // Log exception
            return null
        }
    }
}