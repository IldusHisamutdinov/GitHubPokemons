package com.example.githubclient.mvp.model.cache

import com.example.githubclient.mvp.model.entity.Pokemon
import com.example.githubclient.mvp.model.entity.ReposGitHubUser
import io.reactivex.rxjava3.core.Single

interface IReposUserCache {
    fun addReposUser(listRepos:List<Pokemon>): Single<List<Pokemon>>
    fun getReposUser(): Single<List<ReposGitHubUser>>
}

//fun getReposUser(url:String): Single<Pokemon> // Single<List<ReposGitHubUser>>
