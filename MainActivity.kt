package com.example.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsapi.news.ArticlesListFragment
import com.example.newsapi.news.ArticlesViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fm_container)
        val sharedViewModel = ViewModelProviders.of(this).get(ArticlesViewModel::class.java)

        sharedViewModel.initArticles()

        sharedViewModel.articlesLiveData.observe(this, Observer {
            it?.let {
            }
        })

        // ensures fragments already created will not be created
        if (fragment == null) {
            fragment = ArticlesListFragment()
            // create and commit a fragment transaction
            fm.beginTransaction()
                .add(R.id.fm_container, fragment)
                .commit()
        }
    }
}
