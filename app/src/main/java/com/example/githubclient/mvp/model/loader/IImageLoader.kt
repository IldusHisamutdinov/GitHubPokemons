package com.example.githubclient.mvp.model.loader

interface IImageLoader<T> {
    fun loadInto(url:String, container: T)
}