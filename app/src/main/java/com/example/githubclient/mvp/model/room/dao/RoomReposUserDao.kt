package com.example.githubclient.mvp.model.room.dao

import androidx.room.*
import com.example.githubclient.mvp.model.room.entity.RoomReposUser

@Dao
interface RoomReposUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gitHubUser: RoomReposUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: RoomReposUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(listUser: List<RoomReposUser>)

    @Update
    fun update(gitHubUser: RoomReposUser)

    @Update
    fun update(vararg gitHubUser: RoomReposUser)

    @Update
    fun update(listUser: List<RoomReposUser>)

    @Delete
    fun delete(gitHubUser: RoomReposUser)

    @Delete
    fun delete(vararg gitHubUser: RoomReposUser)

    @Delete
    fun delete(listUser: List<RoomReposUser>)

    @Query("SELECT * FROM RoomReposUser")
    fun getAllUsers(): List<RoomReposUser>

    @Query("SELECT * FROM RoomReposUser WHERE userId = :userId")
    fun getByLogin(userId: String): RoomReposUser?
}