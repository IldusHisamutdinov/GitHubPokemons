package com.example.githubclient.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.githubclient.R
import com.example.githubclient.app.GitHubApp
import com.example.githubclient.mvp.model.REPOS_USER
import com.example.githubclient.mvp.model.api.ApiHolder
import com.example.githubclient.mvp.model.cache.room.RoomReposUserCache
import com.example.githubclient.mvp.model.entity.Pokemon
import com.example.githubclient.mvp.model.entity.ReposGitHubUser
import com.example.githubclient.mvp.model.loader.GlideImageLoader
import com.example.githubclient.mvp.model.loader.IImageLoader
import com.example.githubclient.mvp.model.repository.RetrofitGitHubUsersRepo
import com.example.githubclient.mvp.model.repository.RetrofitReposUserRepo
import com.example.githubclient.mvp.model.room.database.GitHubDatabase
import com.example.githubclient.mvp.presenter.ReposUserPresenter
import com.example.githubclient.mvp.view.ReposUserView
import com.example.githubclient.network.AndroidNetworkStatus
import com.example.githubclient.ui.BackButtonListener
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_repos.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ReposFragment(val imageLoader: IImageLoader<ImageView>) : MvpAppCompatFragment(),
    ReposUserView, BackButtonListener {
    companion object {
        fun newInstance(repos: ReposGitHubUser): ReposFragment {
            val fragment = ReposFragment(GlideImageLoader())
            val bundle = Bundle()
            bundle.putParcelable(REPOS_USER, repos)
            fragment.arguments = bundle
            return fragment
        }

    }

    val presenter by moxyPresenter {
        ReposUserPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitReposUserRepo(ApiHolder.api, AndroidNetworkStatus(context!!), RoomReposUserCache(
                GitHubDatabase.newInstance() as GitHubDatabase) ),
            GitHubApp.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_repos, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.repos = arguments?.getParcelable(REPOS_USER)
    }

    @SuppressLint("SetTextI18n")
    override fun init(pokemon: Pokemon) {
        activity?.title = pokemon.name
        id_pokemon.text = pokemon.id.toString()
        base_experience_pokemon.text =
            "Базовый опыт: ${pokemon.baseExperience.toString()}"
        height_pokemon.text = "Рост: ${pokemon.height.toString()}"
        weight_pokemon.text = "Вес: ${pokemon.weight}"
    }

    override fun loadImage(url: String) {
        imageLoader.loadInto(url, imageView)
    }

    override fun showError(e: Throwable) {
        view?.let { e.message?.let { it1 -> Snackbar.make(it, it1, Snackbar.LENGTH_SHORT).show() } }

    }

    override fun backPressed() = presenter.backPressed()
}