package com.example.githubclient.mvp.model.cache.room

import com.example.githubclient.mvp.model.cache.IUserCache
import com.example.githubclient.mvp.model.entity.ReposGitHubUser
import com.example.githubclient.mvp.model.room.database.GitHubDatabase
import com.example.githubclient.mvp.model.room.entity.RoomUser
import io.reactivex.rxjava3.core.Single

class RoomUsersCache(var db: GitHubDatabase) : IUserCache {


    override fun addUsers(users: List<ReposGitHubUser>) = Single.fromCallable {
        val roomUsers = users.map { user ->
            RoomUser(
                user.id ?: "",
                user.name ?: "",
                user.url ?: ""
            )
        }
        db.userDao.insert(roomUsers)
        users
    }

    override fun getUsers() =   Single.fromCallable {
        db.userDao.getAllUsers().map { roomUser ->
            ReposGitHubUser(
                roomUser.id,
                roomUser.name,
                roomUser.url)
        }
    }


}