package com.rizieq.quizfinalproject.utils

import com.rizieq.quizfinalproject.retrofit.RetrofitClient
import com.rizieq.quizfinalproject.retrofit.RetrofitService
import retrofit2.create

class Common {

    companion object {


        val BASE_URL: String = "http://192.168.43.4/quiz_final/"

        fun getApi(): RetrofitService? {
            return RetrofitClient.getClient(BASE_URL)?.create(
                RetrofitService::class.java
            )
        }
    }
}