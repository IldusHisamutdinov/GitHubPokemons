package com.example.githubclient.mvp.view

import com.example.githubclient.mvp.model.entity.Pokemon
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ReposUserView : MvpView {
    fun init(pokemon: Pokemon)
    fun loadImage(url:String)
    fun showError(e: Throwable)
}