package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.model.entity.Pokemon
import com.example.githubclient.mvp.model.entity.ReposGitHubUser
import com.example.githubclient.mvp.model.repository.IGitHubUsersRepo
import com.example.githubclient.mvp.model.repository.IReposUserRepo
import com.example.githubclient.mvp.view.ReposUserView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class ReposUserPresenter(
    val mainThreadScheduler: Scheduler,
 //   val usersRepo: IGitHubUsersRepo,
    val usersRepo: IReposUserRepo,
    val router: Router) : MvpPresenter<ReposUserView>() {

    var repos:ReposGitHubUser? = null
    var pokemon: Pokemon? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadPokemon()
    }

    fun loadPokemon() {
   //     user?.reposUrl?.let {
            usersRepo.getReposUser(repos?.url.toString())
                .observeOn(mainThreadScheduler)
                .subscribe({
                    pokemon = it
                    viewState.init(it)
                    it.sprites?.front_default?.let { it -> viewState.loadImage(it) }
                },{error->
                    viewState.showError(error)
                })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}

