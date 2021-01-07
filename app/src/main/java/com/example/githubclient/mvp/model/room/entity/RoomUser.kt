package com.example.githubclient.mvp.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomUser(
//    @PrimaryKey
//    var id:String,
//    @ColumnInfo
//    var login:String,
//    @ColumnInfo
//    var avatarUrl:String,
//    @ColumnInfo
//    var reposUrl:String
    @PrimaryKey
    var id:String,
    @ColumnInfo
    var name:String,
    @ColumnInfo
    var url:String
)
