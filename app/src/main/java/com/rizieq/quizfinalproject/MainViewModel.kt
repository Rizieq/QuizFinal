package com.rizieq.quizfinalproject

import android.content.Context
import android.view.Menu
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizieq.quizfinalproject.model.Category
import com.rizieq.quizfinalproject.model.MenuCategory
import com.rizieq.quizfinalproject.retrofit.RetrofitService
import com.rizieq.quizfinalproject.utils.Common
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {


    private val menucategoryData: MutableLiveData<List<MenuCategory>> = MutableLiveData<List<MenuCategory>>()

    var compositeDisposable = CompositeDisposable()
    lateinit var mService: RetrofitService



    fun setMenuCategory(){
        mService = Common.getApi()!!
        compositeDisposable.add(
            mService.getMenuCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<List<MenuCategory>>{
                    override fun accept(menuCategory: List<MenuCategory>?) {
                        menucategoryData.value = menuCategory
                    }

                })
        )
    }
    fun getMenuCategoryData(): MutableLiveData<List<MenuCategory>> {
        return menucategoryData
    }

}