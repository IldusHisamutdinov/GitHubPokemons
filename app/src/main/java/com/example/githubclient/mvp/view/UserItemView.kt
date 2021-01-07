package com.example.githubclient.mvp.view

import com.example.githubclient.mvp.model.entity.Pokemon
import com.example.githubclient.mvp.model.entity.ReposGitHubUser

interface UserItemView:IItemView {
    fun setName(text: ReposGitHubUser)
    fun bind(pokemon: Pokemon)
    fun loadAvatar(url:String)
}