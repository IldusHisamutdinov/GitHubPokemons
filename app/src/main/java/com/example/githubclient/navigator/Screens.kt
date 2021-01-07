package com.example.githubclient.navigator

import com.example.githubclient.mvp.model.entity.GitHubUser
import com.example.githubclient.mvp.model.entity.ReposGitHubUser
import com.example.githubclient.ui.fragments.ReposFragment
import com.example.githubclient.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

//    class UserScreen(val user: ReposGitHubUser) : SupportAppScreen() {
//        override fun getFragment() = ReposFragment.newInstance(user)
//    }

    class ReposScreen(val reposUser: ReposGitHubUser) : SupportAppScreen() {
        override fun getFragment() = ReposFragment.newInstance(reposUser)
    }
}
