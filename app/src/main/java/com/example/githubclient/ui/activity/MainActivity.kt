package com.example.githubclient.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.githubclient.app.GitHubApp
import com.example.githubclient.R
import com.example.githubclient.mvp.presenter.MainPresenter
import com.example.githubclient.mvp.view.MainView
import com.example.githubclient.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {

    val presenter: MainPresenter by moxyPresenter { MainPresenter(GitHubApp.instance.router) }

    private val navigatorHolder = GitHubApp.instance.navigatorHolder
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
    }
}