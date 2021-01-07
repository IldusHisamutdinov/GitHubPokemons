package com.example.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.R
import com.example.githubclient.mvp.model.entity.Pokemon
import com.example.githubclient.mvp.model.entity.ReposGitHubUser
import com.example.githubclient.mvp.model.loader.IImageLoader
import com.example.githubclient.mvp.presenter.list.IUserListPresenter
import com.example.githubclient.mvp.view.UserItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*


class UserRVAdapter(val presenter: IUserListPresenter, val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer, UserItemView {

        override fun setName(text: ReposGitHubUser) = with(containerView) {
            name_pokemon.text = text.name
        }

        override fun bind(pokemon: Pokemon) = with(containerView) {
            name_pokemon.text = pokemon.name
            id_pokemon.text = pokemon.id.toString()
        }


        override fun loadAvatar(url: String) = with(containerView) {
            imageLoader.loadInto(url, img_pokemon)
        }

        override var pos = -1
    }
}
