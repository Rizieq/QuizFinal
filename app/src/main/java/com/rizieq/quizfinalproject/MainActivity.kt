package com.rizieq.quizfinalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizieq.quizfinalproject.adapter.AdapterHome
import com.rizieq.quizfinalproject.model.Category
import com.rizieq.quizfinalproject.retrofit.RetrofitService
import com.rizieq.quizfinalproject.utils.Common
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //RxJava
    var compositeDisposable = CompositeDisposable()
    lateinit var mService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mService = Common.getApi()!!
        getMenu()
    }

    private fun getMenu() {



        compositeDisposable.add(
            mService.getMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<List<Category>>{
                    override fun accept(categories: List<Category>?) {
                        displayMenu(categories)
                    }

                })
        )
    }

    private fun displayMenu(categories: List<Category>?) {
        val adapter = categories?.let { AdapterHome(this, it) }
        listMainActivityRV.setAdapter(adapter)
        listMainActivityRV.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        listMainActivityRV.setHasFixedSize(true)

    }


}
