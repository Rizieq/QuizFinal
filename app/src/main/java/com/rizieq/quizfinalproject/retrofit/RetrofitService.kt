package com.rizieq.quizfinalproject.retrofit

import com.rizieq.quizfinalproject.model.Category
import com.rizieq.quizfinalproject.model.MenuCategory
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @FormUrlEncoded
    @POST("getcategory.php")
    fun getMenu(@Field("menuid") menuID: String?): Observable<List<Category>>


    @FormUrlEncoded
    @POST("addcategory.php")
    fun addMenu(@Field("nama_produk") nama_produk: String?,
                @Field("merk_produk") merk_produk: String?,
                @Field("harga") harga: String?,
                @Field("jenis_produk") jenis_produk: String?): Observable<String>



    @GET("getmenucategory.php")
    fun getMenuCategory(): Observable<List<MenuCategory>>



    @FormUrlEncoded
    @POST("updatecategory.php")
    fun updateMenu(@Field("id_produk") id_produk: String?,
                   @Field("nama_produk") nama_produk: String?,
                   @Field("merk_produk") merk_produk: String?,
                   @Field("harga") harga: String?,
                   @Field("jenis_produk") jenis_produk: String?):Observable<String>

    @FormUrlEncoded
    @POST("deleteproduk.php")
    fun deleteMenu(@Field("id_produk") id_produk: String?):Observable<String>

}
