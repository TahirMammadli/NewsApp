package com.example.newsapi.model

data class NewsResponse(val totalResults: Int = 0,
                        val articles: List<ArticlesItem>?,
                        val status: String = "")