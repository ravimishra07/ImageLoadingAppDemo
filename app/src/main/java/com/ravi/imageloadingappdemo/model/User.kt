package com.ravi.imageloadingappdemo.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.ravi.imageloadingappdemo.model.LinksXX
import com.ravi.imageloadingappdemo.model.ProfileImageX
import com.ravi.imageloadingappdemo.model.SocialX

@Parcelize
data class User(
    @SerializedName("accepted_tos")
    val acceptedTos: Boolean,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("for_hire")
    val forHire: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("instagram_username")
    val instagramUsername: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("links")
    val links: LinksXX,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("portfolio_url")
    val portfolioUrl: String,
    @SerializedName("profile_image")
    val profileImage: ProfileImageX,
    @SerializedName("social")
    val social: SocialX,
    @SerializedName("total_collections")
    val totalCollections: Int,
    @SerializedName("total_likes")
    val totalLikes: Int,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("username")
    val username: String
) : Parcelable