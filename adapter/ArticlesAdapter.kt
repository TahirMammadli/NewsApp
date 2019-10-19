package com.example.newsapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.adapter.ArticlesAdapter.NewsViewHolder
import com.example.newsapi.model.ArticlesItem
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList
import com.example.newsapi.R
import com.example.newsapi.tools.Utils

class ArticlesAdapter(val listener: (ArticlesItem) -> Unit): RecyclerView.Adapter<NewsViewHolder>() {
    private val articles: ArrayList<ArticlesItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout, parent, false)

        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val articlesItem: ArticlesItem = articles[position]

        holder.btnReadMore?.setOnClickListener{view -> listener.invoke(articlesItem) }
        //Setting image
        if (articlesItem.urlToImage != null)
            Picasso.get().load(articlesItem.urlToImage).resize(1400,900).onlyScaleDown().into(holder.ivNews)
        //Setting title of the article
        if (articlesItem.title != null)
            holder.tvTitle?.text = articles[position].title

        //Setting description with cutting the content in order to fit the text in cardview
        if (articlesItem.description != null) {
            if (articles[position].description!!.length > 150)
                holder.tvDescription?.text = articles[position].description?.substring(0, 150) + "..."
            else
                holder.tvDescription?.text = articles[position].description
        }

        if (articlesItem.publishedAt != null){
            holder.tvDate?.text = Utils.convertDate(articlesItem.publishedAt)
        }
    }
    //f27dbe43c4049e98ffc7080b05edf421
    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateArticles(newArticles: List<ArticlesItem>) {
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivNews: ImageView? = null
        var tvTitle: TextView? = null
        var tvDescription: TextView? = null
        var tvDate: TextView? = null
        var btnReadMore: Button? = null

        init {
            ivNews = itemView.findViewById(com.example.newsapi.R.id.iv_news)
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvDescription = itemView.findViewById(R.id.tv_description)
            tvDate = itemView.findViewById(R.id.tv_date)
            btnReadMore = itemView.findViewById(R.id.btn_read_more)
        }

    }
}