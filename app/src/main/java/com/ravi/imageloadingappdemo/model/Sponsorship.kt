package com.ravi.imageloadingappdemo.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.ravi.imageloadingappdemo.model.Sponsor

@Parcelize
data class Sponsorship(
    @SerializedName("impression_urls")
    val impressionUrls: List<String>,
    @SerializedName("sponsor")
    val sponsor: Sponsor,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("tagline_url")
    val taglineUrl: String
) : Parcelable