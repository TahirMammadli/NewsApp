package com.example.newsapi.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.R
import com.example.newsapi.adapter.ArticlesAdapter


class ArticlesListFragment : Fragment() {

    private lateinit var articlesViewModel: ArticlesViewModel
    private lateinit var newsAdapter: ArticlesAdapter
    private var rvArticles: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvArticles = view.findViewById(R.id.rv_articles)
        setupRecyclerView()

        activity?.let {
            articlesViewModel = ViewModelProviders.of(it).get(ArticlesViewModel::class.java)
            articlesViewModel.articlesLiveData.observe(this, Observer { it?.let { newsAdapter.updateArticles(it)}})
        }

    }

    private fun setupRecyclerView() {
        newsAdapter = ArticlesAdapter(listener = { articlesItem ->
            articlesViewModel.setSelectedArticle(articlesItem)
            activity?.supportFragmentManager?.beginTransaction()?.replace(
                R.id.fm_container,
                ArticleDetailsFragment.newInstance(),
                ArticleDetailsFragment.javaClass.name
            )?.addToBackStack(null)?.commit()
        })
        rvArticles?.adapter = newsAdapter
        rvArticles?.layoutManager = LinearLayoutManager(context)
        rvArticles?.itemAnimator = DefaultItemAnimator()
        rvArticles?.isNestedScrollingEnabled = true

    }
}