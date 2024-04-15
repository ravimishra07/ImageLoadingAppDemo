package com.ravi.imageloadingappdemo.di

import android.app.Application
import androidx.room.Room
import com.ravi.imageloadingappdemo.data.local.ImageDatabase
import com.ravi.imageloadingappdemo.data.repository.UnsplashImageRepositoryImpl
import com.ravi.imageloadingappdemo.data.UnsplashImageRepository
import com.ravi.imageloadingappdemo.data.remote.UnsplashApi
import com.ravi.imageloadingappdemo.util.AppDispatcherProvider
import com.ravi.imageloadingappdemo.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideUnsplashApi(): UnsplashApi {
        return Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepository(api: UnsplashApi, db: ImageDatabase): UnsplashImageRepository {
        return UnsplashImageRepositoryImpl(api, db.getDao())
    }


    @Provides
    @Singleton
    fun provideDatabase(app: Application): ImageDatabase {
        return Room
            .databaseBuilder(app, ImageDatabase::class.java, "img_db")
            .build()
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {
        return AppDispatcherProvider()
    }












//
//    @Singleton
//    @Provides
//    fun provideHttpClient() : OkHttpClient {
//        return OkHttpClient.Builder()
//            .readTimeout(15, TimeUnit.SECONDS)
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideConverterFactory(): GsonConverterFactory {
//        return GsonConverterFactory.create()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofitInstance(
//        okHttpClient: OkHttpClient,
//        gsonConverterFactory: GsonConverterFactory
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(gsonConverterFactory)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideApiService(retrofit: Retrofit): RetrofitApi {
//        return retrofit.create(RetrofitApi::class.java)
//    }
}