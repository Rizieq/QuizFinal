package com.rizieq.quizfinalproject.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizieq.quizfinalproject.R
import com.rizieq.quizfinalproject.detail.DetailMenuActivity
import com.rizieq.quizfinalproject.model.Category
import com.rizieq.quizfinalproject.model.MenuCategory
import com.rizieq.quizfinalproject.utils.Common


class AdapterHome(private val context: Context, private val category: List<MenuCategory>) :
    RecyclerView.Adapter<AdapterHome.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHome.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_menu_produk, parent, false))
    }

    override fun getItemCount(): Int = category.size

    override fun onBindViewHolder(holder: AdapterHome.ViewHolder, position: Int) {
        holder.jenisProduk.text = category[position].nama_role

        holder.itemView.setOnClickListener {
            Common.currentMenuCategory = category.get(position)
            context.startActivity(Intent(context,DetailMenuActivity::class.java))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var jenisProduk: TextView


        init {


            jenisProduk = itemView.findViewById(R.id.jenisProdukMainActivityTv)

        }
    }
}