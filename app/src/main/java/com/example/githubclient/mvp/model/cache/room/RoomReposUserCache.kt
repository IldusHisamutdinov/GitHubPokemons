package com.example.githubclient.mvp.model.cache.room

import com.example.githubclient.mvp.model.cache.IReposUserCache
import com.example.githubclient.mvp.model.entity.Pokemon
import com.example.githubclient.mvp.model.entity.ReposGitHubUser
import com.example.githubclient.mvp.model.entity.Sprites
import com.example.githubclient.mvp.model.room.database.GitHubDatabase
import com.example.githubclient.mvp.model.room.entity.RoomReposUser
import io.reactivex.rxjava3.core.Single
import kotlinx.android.parcel.RawValue

class RoomReposUserCache(val db: GitHubDatabase) : IReposUserCache {
    override fun addReposUser(listRepos:List<Pokemon>) = Single.fromCallable {
        val reposUser = listRepos.map { repo ->
            RoomReposUser(
                (repo.id ?: "") as Int,
                repo.name ?: "",
                repo.baseExperience ?: 0,
                (repo.height ?: "") as Int,
                (repo.isDefault ?: "") as Boolean,
                (repo.order ?: "") as Int,
                (repo.weight ?: "") as Int
            )
        }
        db.reposUserDao.insert(reposUser)
        listRepos
    }

    override fun getReposUser()  = Single.fromCallable {
        db.reposUserDao.getAllUsers().map { roomRepos ->
            ReposGitHubUser(
                roomRepos.id.toString(),
                roomRepos.name,
                roomRepos.baseExperience.toString()

            )
        }
    }
}