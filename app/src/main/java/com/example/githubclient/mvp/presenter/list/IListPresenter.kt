package com.example.githubclient.mvp.presenter.list

import com.example.githubclient.mvp.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount():Int
}