package com.example.newsapi.news

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer

import com.example.newsapi.R
import com.example.newsapi.tools.Utils
import com.squareup.picasso.Picasso

class ArticleDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleDetailsFragment()
    }

    private var ivArticle: ImageView? = null
    private var tvTitle: TextView? = null
    private var tvDescription: TextView? = null
    private var tvDate: TextView? = null
    private var tvSource: TextView? = null
    private var tvContent: TextView? = null
    private var tvUrl: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            val articlesViewModel = ViewModelProviders.of(it).get(ArticlesViewModel::class.java)
            initViews()
            articlesViewModel.selectedArticle.observe(this, Observer {
                it?.let {
                    Log.d("ArticleDetailsFragment", "$it")
                    Picasso.get().load(it.urlToImage).into(ivArticle)
                    tvTitle?.text = it.title
                    tvDescription?.text = it.description
                    tvDate?.text = "Published at: " + Utils.convertDate(it.publishedAt)
                    tvSource?.text = "Source: " +it.source?.name
                    tvContent?.text = it.content
                    tvUrl?.text = it.title
                }
            })
        }
    }

    private fun initViews() {
        ivArticle = view?.findViewById(R.id.iv_article)
        tvTitle = view?.findViewById(R.id.tv_title)
        tvDescription = view?.findViewById(R.id.tv_description)
        tvDate = view?.findViewById(R.id.tv_date)
        tvSource = view?.findViewById(R.id.tv_source)
        tvContent = view?.findViewById(R.id.tv_content)
        tvUrl = view?.findViewById(R.id.tv_url)
    }
}
