package com.example.newsapi.tools

import java.text.SimpleDateFormat

val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
val output = SimpleDateFormat("dd MMMM, yyyy")

class Utils {

    companion object {
        fun convertDate(date: String?): String {
            val stringDate = input.parse(date)
            return output.format(stringDate).toString()
        }
    }
}