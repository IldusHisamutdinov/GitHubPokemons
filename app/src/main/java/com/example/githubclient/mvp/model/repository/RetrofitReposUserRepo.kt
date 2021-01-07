package com.example.githubclient.mvp.model.repository

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IReposUserCache
import com.example.githubclient.network.INetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitReposUserRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IReposUserCache
) : IReposUserRepo {

    override fun getReposUser(url: String) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getReposUser(url)//.flatMap { repos ->
 //               cache.addReposUser(repos)
 //           }
        }else{
            cache.getReposUser()
            api.getReposUser(url)
        }
    }.subscribeOn(Schedulers.io())


    // api.getReposUser(url).subscribeOn(Schedulers.io())
}