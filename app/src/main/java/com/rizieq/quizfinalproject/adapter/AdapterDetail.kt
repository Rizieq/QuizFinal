package com.rizieq.quizfinalproject.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Update
import com.rizieq.quizfinalproject.R
import com.rizieq.quizfinalproject.model.Category
import com.rizieq.quizfinalproject.updatedelete.UpdateDeleteActivity
import com.rizieq.quizfinalproject.utils.Common

class AdapterDetail(private val context: Context, private val category: List<Category>) :
    RecyclerView.Adapter<AdapterDetail.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDetail.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_produk,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = category.size

    override fun onBindViewHolder(holder: AdapterDetail.ViewHolder, position: Int) {
        holder.namaProduk.text = category[position].nama_produk
        holder.merkProduk.text = category[position].merk_produk
        holder.hargaProduk.text = category[position].harga
        holder.jenisProduk.text = category[position].jenis_produk

        holder.itemView.setOnClickListener {
            Common.currentCategory = category.get(position)
            context.startActivity(Intent(context,UpdateDeleteActivity::class.java))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var namaProduk: TextView
        internal var merkProduk: TextView
        internal var hargaProduk: TextView
        internal var jenisProduk: TextView


        init {

            namaProduk = itemView.findViewById(R.id.namaProdukMainActivityTv)
            merkProduk = itemView.findViewById(R.id.merkProdukMainActivityTv)
            hargaProduk = itemView.findViewById(R.id.hargaProdukMainActivityTv)
            jenisProduk = itemView.findViewById(R.id.jenisProdukMainActivityTv)

        }

    }
}