package com.example.newsapi.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapi.model.ArticlesItem
import com.example.newsapi.model.NewsResponse
import com.example.newsapi.service.NewsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArticlesViewModel : ViewModel() {

    val articlesLiveData: MutableLiveData<List<ArticlesItem>> = MutableLiveData()
    val selectedArticle: MutableLiveData<ArticlesItem> = MutableLiveData()

    fun initArticles() {
        NewsApi.create().getArticles().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                Log.d("NewsViewModel", "onResponse: $response")
                if (response.isSuccessful) {
                    articlesLiveData.value = response.body()?.articles
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("NewsViewModel", "onFailure: ${t.message}")

                articlesLiveData.value = null
            }
        })
    }

    fun setSelectedArticle(articlesItem: ArticlesItem) {
        selectedArticle.value = articlesItem
    }


}