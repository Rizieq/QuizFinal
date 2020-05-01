package com.rizieq.quizfinalproject.updatedelete

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizieq.quizfinalproject.model.MenuCategory
import com.rizieq.quizfinalproject.retrofit.RetrofitService
import com.rizieq.quizfinalproject.utils.Common
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class UpdateDeleteViewModel: ViewModel() {

    private val updateMenuCategoryData: MutableLiveData<String> = MutableLiveData<String>()
    private val deleteMenuCategoryData: MutableLiveData<String> = MutableLiveData<String>()

    var compositeDisposable = CompositeDisposable()
    lateinit var mService: RetrofitService



    fun setUpdateMenuCategory(nama: String, harga: String, merk: String){
        mService = Common.getApi()!!
        compositeDisposable.add(
            mService.updateMenu(Common.currentCategory.id_produk,
                nama,
                merk,
                harga,
                Common.currentCategory.jenis_produk
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<String> {
                    override fun accept(menuCategory: String) {
                        updateMenuCategoryData.value = menuCategory
                    }

                })
        )
    }
    fun getUpdateMenuCategoryData(): MutableLiveData<String> {
        return updateMenuCategoryData
    }

    fun setDeleteMenuCategory(){
        mService = Common.getApi()!!
        compositeDisposable.add(
            mService.deleteMenu(Common.currentCategory.id_produk
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<String> {
                    override fun accept(menuCategory: String) {
                        deleteMenuCategoryData.value = menuCategory
                    }

                })
        )
    }
    fun getDeleteMenuCategoryData(): MutableLiveData<String> {
        return deleteMenuCategoryData
    }

}