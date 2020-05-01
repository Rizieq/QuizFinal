package com.rizieq.quizfinalproject.updatedelete

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rizieq.quizfinalproject.R
import com.rizieq.quizfinalproject.model.MenuCategory
import com.rizieq.quizfinalproject.utils.Common
import kotlinx.android.synthetic.main.activity_update_delete.*
import java.util.*

class UpdateDeleteActivity : AppCompatActivity() {

    var menu_data_for_ger_key =
        HashMap<String, String>()
    var menu_data_for_ger_value =
        HashMap<String, String>()
    var selected_category = ""
    var menu_data: List<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        if (Common.currentCategory != null){
            selected_category = Common.currentCategory.jenis_produk
        } else{
          Toast.makeText(this,"Gagal",Toast.LENGTH_SHORT).show()
        }

        val viewModel: UpdateDeleteViewModel = ViewModelProviders.of(this)
            .get(UpdateDeleteViewModel::class.java)

//        jenisProdukUpdateDeleteActivityMS.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<Any?> { view, position, id, item ->
//            selected_category = menu_data_for_ger_key[menu_data[position]]!!
//
//        })

        updateProdukAddItemBtn.setOnClickListener {
            updateData(namaProdukUpdateDeleteActivityET.text.toString(),
                merkProdukUpdateDeleteActivityET.text.toString(),
                hargaProdukUpdateDeleteActivityET.text.toString(),
                viewModel)
        }

        deleteProdukAddItemBtn.setOnClickListener {
            deleteData(viewModel)
        }
        setSpinnerMenu()
        setProductInfo()

    }

    private fun setProductInfo() {
        if (Common.currentCategory != null){
            namaProdukUpdateDeleteActivityET.setText(Common.currentCategory.nama_produk)
            hargaProdukUpdateDeleteActivityET.setText(Common.currentCategory.harga)
            merkProdukUpdateDeleteActivityET.setText(Common.currentCategory.merk_produk)
//            jenisProdukUpdateDeleteActivityMS.setSelectedIndex(menu_data.indexOf(menu_data_for_ger_value[Common.currentMenuCategory.id_role]))
        }
    }

    private fun setSpinnerMenu() {
        for (category:MenuCategory in Common.menuList) {
            menu_data_for_ger_key[category.nama_role] = category.id_role
            menu_data_for_ger_value[category.id_role] = category.nama_role
//            menu_data.add(category.nama_role)
            menu_data = listOf(category.nama_role)

        }

//        jenisProdukUpdateDeleteActivityMS.setItems(menu_data)
    }

    private fun deleteData(viewModel: UpdateDeleteViewModel) {
        viewModel.setDeleteMenuCategory()
        viewModel.getDeleteMenuCategoryData().observe(this, object : Observer<String>{
            override fun onChanged(t: String?) {
                Toast.makeText(this@UpdateDeleteActivity,t,Toast.LENGTH_LONG).show()
                finish()
            }

        })
    }

    private fun updateData(
        nama: String,
        merk: String,
        harga: String,
        viewModel: UpdateDeleteViewModel
    ) {
        viewModel.setUpdateMenuCategory(nama, harga, merk)
        viewModel.getUpdateMenuCategoryData().observe(this, object : Observer<String>{
            override fun onChanged(t: String?) {
                Toast.makeText(this@UpdateDeleteActivity,t,Toast.LENGTH_LONG).show()
                finish()
            }

        })


    }
}
