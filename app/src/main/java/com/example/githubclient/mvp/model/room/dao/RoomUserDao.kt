package com.example.githubclient.mvp.model.room.dao

import androidx.room.*
import com.example.githubclient.mvp.model.room.entity.RoomUser


@Dao
interface RoomUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gitHubUser: RoomUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: RoomUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(listUser: List<RoomUser>)

    @Update
    fun update(gitHubUser: RoomUser)

    @Update
    fun update(vararg gitHubUser: RoomUser)

    @Update
    fun update(listUser: List<RoomUser>)

    @Delete
    fun delete(gitHubUser: RoomUser)

    @Delete
    fun delete(vararg gitHubUser: RoomUser)

    @Delete
    fun delete(listUser: List<RoomUser>)

    @Query("SELECT * FROM RoomUser")
    fun getAllUsers():List<RoomUser>

    @Query("SELECT * FROM RoomUser WHERE name = :login LIMIT 2")
    fun getByLogin(login:String): RoomUser?
}