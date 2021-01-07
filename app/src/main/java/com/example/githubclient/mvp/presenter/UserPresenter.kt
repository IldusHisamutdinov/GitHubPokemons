package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.model.entity.GitHubUser
import com.example.githubclient.mvp.model.entity.ReposGitHubUser
import com.example.githubclient.mvp.model.repository.IGitHubUsersRepo
import com.example.githubclient.mvp.presenter.list.IReposUserPresenter
import com.example.githubclient.mvp.view.ReposUserItemView
import com.example.githubclient.mvp.view.UserView
import com.example.githubclient.navigator.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

//class UserPresenter(
//    val mainThreadScheduler: Scheduler,
//    val usersRepo: IGitHubUsersRepo,
//    val router: Router
//) :
//    MvpPresenter<UserView>() {
//
//    class ReposUserListPresenter : IReposUserPresenter {
//        var reposUserList = mutableListOf<ReposGitHubUser>()
//        override var itemClickListener: ((ReposUserItemView) -> Unit)? = null
//
//
//        override fun bindView(itemView: ReposUserItemView) {
//            val repos = reposUserList[itemView.pos]
//            repos.name?.let { itemView.setReposName(it) }
//        }
//
//        override fun getCount() = reposUserList.size
//    }
//
//    val reposUserPresenter = ReposUserListPresenter()
//    var user: GitHubUser? = null
//
//    override fun onFirstViewAttach() {
//        super.onFirstViewAttach()
//        viewState.init()
//        loadRepos()
//        reposUserPresenter.itemClickListener = {
//            val repos = reposUserPresenter.reposUserList[it.pos]
//            router.navigateTo(Screens.ReposScreen(repos))
//        }
//    }
//
//    fun loadRepos() {
//        user?.reposUrl?.let {
//            usersRepo.getReposUser(it)
//                .observeOn(mainThreadScheduler)
//                .subscribe({repos->
//                    reposUserPresenter.reposUserList.clear()
//                    reposUserPresenter.reposUserList.addAll(repos)
//                    viewState.updateList()
//                },{error->
//                    viewState.showError(error)
//                })
//        }
//    }
//
//    fun backPressed(): Boolean {
//        router.exit()
//        return true
//    }
//
//}