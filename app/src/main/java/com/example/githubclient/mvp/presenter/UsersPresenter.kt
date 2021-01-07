package com.example.githubclient.mvp.presenter


import com.example.githubclient.mvp.model.entity.GitHubUser
import com.example.githubclient.mvp.model.entity.ReposGitHubUser
import com.example.githubclient.mvp.model.repository.IGitHubUsersRepo
import com.example.githubclient.mvp.model.repository.IReposUserRepo
import com.example.githubclient.mvp.presenter.list.IUserListPresenter
import com.example.githubclient.mvp.view.UserItemView
import com.example.githubclient.mvp.view.UsersView
import com.example.githubclient.navigator.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    val mainThreadScheduler: Scheduler,
    val usersRepo: IGitHubUsersRepo,
    val userRepo: IReposUserRepo,
    val router: Router
) :
    MvpPresenter<UsersView>() {
//
//     lateinit var mainThreadScheduler: Scheduler
//    lateinit var usersRepo: IGitHubUsersRepo
//    lateinit var userRepo: IReposUserRepo
//    lateinit var router: Router

   inner class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<ReposGitHubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

  //      override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setName(user)
            userRepo.getReposUser(user.url.toString()).observeOn(mainThreadScheduler)
                .subscribe({pokemon ->
                    view.bind(pokemon)
                    pokemon.sprites?.front_default?.let {
                        view.loadAvatar(it)
                    }
                },{
                    viewState.snowError(it)
                })
        }

        override fun getCount() = users.size

    }

    val usersListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = {
  //          val user: GitHubUser = usersListPresenter.users[it.pos]
            router.navigateTo(Screens.ReposScreen(usersListPresenter.users[it.pos]))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                repos.results?.let { it -> usersListPresenter.users.addAll(it) }
                 viewState.updateList()},
                { error -> println("Ошибка: ${error}") })

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}