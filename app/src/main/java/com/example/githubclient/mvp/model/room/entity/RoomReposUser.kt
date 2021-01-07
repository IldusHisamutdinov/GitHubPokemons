package com.example.githubclient.mvp.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.githubclient.mvp.model.entity.Other
import com.example.githubclient.mvp.model.entity.Sprites
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.RawValue

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomReposUser(
//    @PrimaryKey
//    var id: String,
//    @ColumnInfo
//    var name: String,
//    @ColumnInfo
//    var forks: Int,
//    @ColumnInfo
//    var description: String,
//    @ColumnInfo
//    var userId: String? = null
    @PrimaryKey
    var id: Int,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var baseExperience: Int,
    @ColumnInfo
    var height: Int,
    @ColumnInfo
    var isDefault: Boolean,
    @ColumnInfo
    var order: Int,
    @ColumnInfo
    var weight: Int,
//    @ColumnInfo
//    var sprites: @RawValue Sprites,
    @ColumnInfo
    var userId: String? = null
) {
}

