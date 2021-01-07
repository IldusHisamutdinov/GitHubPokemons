package com.example.githubclient.mvp.model.repository

import com.example.githubclient.mvp.model.entity.Pokemon
import io.reactivex.rxjava3.core.Single

interface IReposUserRepo {
    fun getReposUser(url:String): Single<Pokemon> // Single<List<ReposGitHubUser>>
}