package com.rizieq.quizfinalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rizieq.quizfinalproject.adapter.AdapterHome
import com.rizieq.quizfinalproject.model.Category
import com.rizieq.quizfinalproject.model.MenuCategory
import com.rizieq.quizfinalproject.utils.Common
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)

        setRequestServer(viewModel)


    }


    private fun setRequestServer(viewModel: MainViewModel) {
        viewModel.setMenuCategory()
        viewModel.getMenuCategoryData().observe(this, object : Observer<List<MenuCategory>>{
            override fun onChanged(menuCategory: List<MenuCategory>?) {
                if (menuCategory != null){
                    Common.menuList = menuCategory
                    displayMenu(menuCategory)
                }
            }

        })
    }




    private fun displayMenu(categories: List<MenuCategory>?) {
        val adapter = categories?.let { AdapterHome(this, it) }
        listMainActivityRV.setAdapter(adapter)
        listMainActivityRV.setLayoutManager(
            StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
        )
//        listMainActivityRV.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        listMainActivityRV.setHasFixedSize(true)

    }


}
