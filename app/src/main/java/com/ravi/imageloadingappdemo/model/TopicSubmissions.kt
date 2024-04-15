package com.ravi.imageloadingappdemo.model



import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ravi.imageloadingappdemo.model.Film
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopicSubmissions(
    @SerializedName("film")
    val film: Film
) : Parcelable