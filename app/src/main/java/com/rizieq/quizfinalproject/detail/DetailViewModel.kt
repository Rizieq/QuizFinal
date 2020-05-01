package com.rizieq.quizfinalproject.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizieq.quizfinalproject.model.Category
import com.rizieq.quizfinalproject.retrofit.RetrofitService
import com.rizieq.quizfinalproject.utils.Common
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class DetailViewModel: ViewModel() {

    private val categoryData: MutableLiveData<List<Category>> = MutableLiveData<List<Category>>()
    private val itemCategoryData: MutableLiveData<String> = MutableLiveData<String>()

    var compositeDisposable = CompositeDisposable()
    lateinit var mService: RetrofitService

    fun setCategory(){
        mService = Common.getApi()!!
        compositeDisposable.add(
            mService.getMenu(Common.currentMenuCategory?.id_role.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<List<Category>> {
                    override fun accept(categories: List<Category>?) {
                        categoryData.value = categories
                    }

                })
        )

    }

    fun getCategoryData(): MutableLiveData<List<Category>> {
        return categoryData
    }

    fun setItemCategory(nama_produk: String, merk_produk: String, harga: String, jenis_produk: String){
        mService = Common.getApi()!!
        compositeDisposable.add(
            mService.addMenu(nama_produk, merk_produk, harga, jenis_produk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<String>{
                    override fun accept(t: String?) {
                        itemCategoryData.value = t
                        Log.d("LOG_Succes ",t)
                    }

                }, object : Consumer<Throwable>{
                    override fun accept(t: Throwable?) {
                        itemCategoryData.value = t?.message
                        Log.d("LOG_Error ",t?.localizedMessage)
                    }

                })
        )
    }

    fun getItemCategoryData(): MutableLiveData<String>{
        return itemCategoryData
    }
}