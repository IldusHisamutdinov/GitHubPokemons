package com.example.githubclient.mvp.model.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubclient.mvp.model.room.dao.RoomReposUserDao
import com.example.githubclient.mvp.model.room.dao.RoomUserDao
import com.example.githubclient.mvp.model.room.entity.RoomReposUser
import com.example.githubclient.mvp.model.room.entity.RoomUser
import java.lang.RuntimeException

@Database(entities = [RoomUser::class, RoomReposUser::class], version = 1)
abstract class GitHubDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "database.db"
        private var instance: GitHubDatabase? = null
        fun newInstance() = instance ?: RuntimeException("База данных не создана!")
        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    GitHubDatabase::class.java,
                    DB_NAME
                ).build()
            }
        }
    }
    abstract val userDao: RoomUserDao
    abstract val reposUserDao: RoomReposUserDao
}