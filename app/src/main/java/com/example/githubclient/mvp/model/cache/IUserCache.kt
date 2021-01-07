package com.example.githubclient.mvp.model.cache

import com.example.githubclient.mvp.model.entity.GitHubUser
import com.example.githubclient.mvp.model.entity.ReposGitHubUser
import io.reactivex.rxjava3.core.Single

interface IUserCache {
    fun addUsers(users: List<ReposGitHubUser>): Single<List<ReposGitHubUser>>
    fun getUsers(): Single<List<ReposGitHubUser>>
}