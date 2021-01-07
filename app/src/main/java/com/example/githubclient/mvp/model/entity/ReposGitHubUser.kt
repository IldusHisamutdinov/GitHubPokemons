package com.example.githubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReposGitHubUser(
//    @Expose
//    val id: String? = null,
//    @Expose
//    val name: String? = null,
//    @Expose
//    val forks: Int? = null,
//    @Expose
//    val description: String? = null
    @Expose
    val id:String? = null,
    @Expose
    val name:String? = null,
    @Expose
    val url:String? = null
) : Parcelable {
}