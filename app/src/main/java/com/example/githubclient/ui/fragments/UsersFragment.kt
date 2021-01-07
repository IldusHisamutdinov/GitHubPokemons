package com.example.githubclient.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubclient.app.GitHubApp
import com.example.githubclient.R
import com.example.githubclient.mvp.model.APP_NAME
import com.example.githubclient.mvp.model.api.ApiHolder
import com.example.githubclient.mvp.model.cache.room.RoomReposUserCache
import com.example.githubclient.mvp.model.cache.room.RoomUsersCache
import com.example.githubclient.mvp.model.loader.GlideImageLoader
import com.example.githubclient.mvp.model.repository.IReposUserRepo
import com.example.githubclient.mvp.model.repository.RetrofitGitHubUsersRepo
import com.example.githubclient.mvp.model.repository.RetrofitReposUserRepo
import com.example.githubclient.mvp.model.room.database.GitHubDatabase
import com.example.githubclient.mvp.presenter.UsersPresenter
import com.example.githubclient.mvp.view.UsersView
import com.example.githubclient.network.AndroidNetworkStatus
import com.example.githubclient.ui.BackButtonListener
import com.example.githubclient.ui.adapter.UserRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {


    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter( AndroidSchedulers.mainThread(),
    RetrofitGitHubUsersRepo(ApiHolder.api, AndroidNetworkStatus(context!!),
    RoomUsersCache(GitHubDatabase.newInstance() as GitHubDatabase)),
           RetrofitReposUserRepo(ApiHolder.api, AndroidNetworkStatus(context!!),
               RoomReposUserCache(GitHubDatabase.newInstance() as GitHubDatabase)),
    GitHubApp.instance.router)
    }


    var adapter: UserRVAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_users, null)


    override fun init() {
        users_recycler_view.layoutManager =
            GridLayoutManager(context, 2)
        adapter = UserRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        users_recycler_view.adapter = adapter

    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun snowError(error: Throwable) {
        Log.d("ОШИБКА RETROFIT", error.message.toString())
    }

    override fun onResume() {
        super.onResume()
        activity?.title = APP_NAME
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}