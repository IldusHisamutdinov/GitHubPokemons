package com.example.githubclient.mvp.model.repository

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IUserCache
import com.example.githubclient.mvp.model.entity.GitHubUser
import com.example.githubclient.mvp.model.entity.Pokemon
import com.example.githubclient.network.INetworkStatus
import io.reactivex.rxjava3.core.Single

import io.reactivex.rxjava3.schedulers.Schedulers


class RetrofitGitHubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IUserCache

) : IGitHubUsersRepo {
    override fun getUsers(): Single<GitHubUser> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.loadUsers()//.flatMap { users ->
//               cache.addUsers(users)
//            }
        } else {
            cache.getUsers()
            api.loadUsers()
        }
    }.subscribeOn(Schedulers.io())

}
