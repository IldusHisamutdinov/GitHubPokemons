package com.example.githubclient.mvp.model.api

import com.example.githubclient.mvp.model.entity.GitHubUser
import com.example.githubclient.mvp.model.entity.Pokemon
import com.example.githubclient.mvp.model.entity.ReposGitHubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {
    @GET("pokemon/?limit=50")
    fun loadUsers(): Single<GitHubUser>

    @GET
    fun getReposUser(@Url url:String):Single<Pokemon>
}