package com.ravi.imageloadingappdemo.util;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
public class CacheImages {
    private final LruCache<String, Bitmap> memoryCache;
    int key;
    public LruCache<String, Bitmap> getMemoryCache() {
        return memoryCache;
    }

    public int getKey() {
        return key;
    }

    public CacheImages(LruCache<String, Bitmap> memoryCache, int key) {
        this.memoryCache = memoryCache;
        this.key = key;
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            memoryCache.put(key, bitmap);
            Log.d("cached", " new  addBitmapToMemoryCache");
        }else{
            Log.d("cached", "old addBitmapToMemoryCache");
        }
    }
    public Bitmap getBitmapFromMemCache(String key) {
        return memoryCache.get(key);
    }
}
