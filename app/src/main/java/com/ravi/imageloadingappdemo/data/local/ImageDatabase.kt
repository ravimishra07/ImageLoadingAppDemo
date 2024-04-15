package com.ravi.imageloadingappdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ravi.imageloadingappdemo.model.ImageData

@Database(entities = [ImageData::class], version = 1, exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun getDao(): ImageDao
}