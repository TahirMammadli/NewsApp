package com.example.newsapi.model

data class ArticlesItem(val publishedAt: String?,
                        val author: String?,
                        var urlToImage: String?,
                        val description: String?,
                        val source: Source?,
                        var title: String?,
                        val url: String?,
                        val content: String?)
