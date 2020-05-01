package com.rizieq.quizfinalproject.utils

import android.view.Menu
import com.rizieq.quizfinalproject.model.Category
import com.rizieq.quizfinalproject.model.MenuCategory
import com.rizieq.quizfinalproject.retrofit.RetrofitClient
import com.rizieq.quizfinalproject.retrofit.RetrofitService
import java.util.*

class Common {

    companion object {


        val BASE_URL: String = "http://192.168.43.4/quiz_final/"
        var menuList: List<MenuCategory> = ArrayList<MenuCategory>()
        lateinit var currentCategory: Category
        lateinit var currentMenuCategory: MenuCategory

        fun getApi(): RetrofitService? {
            return RetrofitClient.getClient(BASE_URL)?.create(
                RetrofitService::class.java
            )
        }
    }
}