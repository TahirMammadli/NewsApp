package com.example.newsapi.service

import com.example.newsapi.model.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsApi {

    @GET("top-headlines?country=us&apiKey=39bf5b151a784c668ba07eeb596de115")
    fun getArticles(): Call<NewsResponse>

    companion object Factory {
        fun create(): NewsApi {
            val interceptor = HttpLoggingInterceptor()
                interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://newsapi.org/v2/")
//                .client(client)
                .build()

            return retrofit.create(NewsApi::class.java)
        }
    }
}