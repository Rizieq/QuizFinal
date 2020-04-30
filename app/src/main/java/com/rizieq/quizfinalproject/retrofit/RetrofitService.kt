package com.rizieq.quizfinalproject.retrofit

import com.rizieq.quizfinalproject.model.Category
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitService {

    @GET("getcategory.php")
    fun getMenu(): Observable<List<Category>>

}