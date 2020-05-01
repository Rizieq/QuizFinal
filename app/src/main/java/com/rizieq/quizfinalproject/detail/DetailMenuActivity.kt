package com.rizieq.quizfinalproject.detail

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import com.rizieq.quizfinalproject.R
import com.rizieq.quizfinalproject.adapter.AdapterDetail
import com.rizieq.quizfinalproject.model.Category
import com.rizieq.quizfinalproject.utils.Common
import kotlinx.android.synthetic.main.activity_detail_menu.*
import kotlinx.android.synthetic.main.item_add_category.view.*
import java.util.*

class DetailMenuActivity : AppCompatActivity() {


    var suggestList: List<String> = ArrayList()
    var localeDataSource: List<Category> = ArrayList<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_menu)



        var viewModel: DetailViewModel = ViewModelProviders.of(this)
            .get(DetailViewModel::class.java)

        searchDetailActivityMSB.setHint("Masukkan Nama Produk Disini")
        setRequestServer(viewModel)

        searchDetailActivityMSB.setCardViewElevation(10)
        searchDetailActivityMSB.addTextChangeListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val suggest: MutableList<String> =
                    ArrayList()
                for (search in suggestList) {
                    if (search.toLowerCase().contains(searchDetailActivityMSB.getText().toLowerCase())) suggest.add(
                        search
                    )
                }
                searchDetailActivityMSB.setLastSuggestions(suggest)
            }

        })

        searchDetailActivityMSB.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener{
            override fun onButtonClicked(buttonCode: Int) {

            }

            override fun onSearchStateChanged(enabled: Boolean) {
                listDetailActivityRV
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                startSerch(text)
            }

        })


        Log.d("LOG_Data ",Common.currentMenuCategory?.id_role.toString())
        addCategoryMainActivityFB.setOnClickListener {
            showAddCategoryDialog()
        }



    }

    private fun startSerch(text: CharSequence?) {
        val result: MutableList<Category> = ArrayList<Category>()
        for (category: Category in localeDataSource) if (category.nama_produk.contains(text.toString())) result.add(category)
        val searchAdapter = AdapterDetail(this, result)
        listDetailActivityRV.setAdapter(searchAdapter)
    }


    private fun showAddCategoryDialog() {

        var viewModel: DetailViewModel = ViewModelProviders.of(this)
            .get(DetailViewModel::class.java)
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Add New Produk")


        val view: View = LayoutInflater.from(this).inflate(R.layout.item_add_category,null)



        builder.setView(view)
        builder.setPositiveButton("Add", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (view.namaProdukAddItemET.text.toString().isEmpty()){
                    Toast.makeText(this@DetailMenuActivity,"Nama Produk tidak boleh kosong !",Toast.LENGTH_SHORT).show()
                    return
                }
                if (view.merkProdukAddItemET.text.toString().isEmpty()){
                    Toast.makeText(this@DetailMenuActivity,"Merk Produk tidak boleh kosong !",Toast.LENGTH_SHORT).show()
                    return
                }
                if (view.hargaProdukAddItemET.text.toString().isEmpty()){
                    Toast.makeText(this@DetailMenuActivity,"Harga Produk tidak boleh kosong !",Toast.LENGTH_SHORT).show()
                    return
                }


               viewModel.setItemCategory(view.namaProdukAddItemET.text.toString(),
                   view.merkProdukAddItemET.text.toString(),
                   view.hargaProdukAddItemET.text.toString(),
                   Common.currentMenuCategory?.id_role.toString())
                viewModel.getItemCategoryData().observe(this@DetailMenuActivity, object : Observer<String>{
                    override fun onChanged(message: String?) {
                        Toast.makeText(this@DetailMenuActivity,message,Toast.LENGTH_SHORT).show()
                        setRequestServer(viewModel)
                    }

                })


            }

        }).setNegativeButton("Cancel",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }

        }).show()
    }

    override fun onResume() {
        var viewModel: DetailViewModel = ViewModelProviders.of(this)
            .get(DetailViewModel::class.java)
        setRequestServer(viewModel)
        super.onResume()
    }


    private fun setRequestServer(viewModel: DetailViewModel) {

        viewModel.setCategory()
        viewModel.getCategoryData().observe(this, object : Observer<List<Category>>{
            override fun onChanged(categories: List<Category>?) {
                if (categories != null){
                    displayMenu(categories)
                    buildSuggestList(categories)
                }
            }

        })
    }

    private fun buildSuggestList(categories: List<Category>) {
        for (category:Category in categories){
            Log.d("LOG_Data_Index ", listOf(category.nama_produk).toString())
            suggestList = listOf(category.nama_produk)

        }
        searchDetailActivityMSB.setLastSuggestions(suggestList)
    }

    private fun displayMenu(categories: List<Category>) {
        localeDataSource = categories
        val adapterDetail = AdapterDetail(this,categories)
        listDetailActivityRV.adapter = adapterDetail
        listDetailActivityRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        listDetailActivityRV.setHasFixedSize(true)
    }
}
