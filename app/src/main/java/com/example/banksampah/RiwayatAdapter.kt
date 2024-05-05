package com.example.banksampah

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RiwayatAdapter(private val riwayatList: ArrayList<RiwayatData>) : RecyclerView.Adapter<RiwayatAdapter.MyViewHolder>() {

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val alamat = itemView.findViewById<TextView>(R.id.tvAlamat)
        val berat = itemView.findViewById<TextView>(R.id.tvBerat)
        val catatan = itemView.findViewById<TextView>(R.id.tvNote)
        val harga = itemView.findViewById<TextView>(R.id.tvHarga)
        val kategori = itemView.findViewById<TextView>(R.id.tvKategori)
        val pengguna = itemView.findViewById<TextView>(R.id.tvPengguna)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.riwayat_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = riwayatList[position]
        holder.alamat.text =  currentitem.alamat
        holder.berat.text = currentitem.berat
        holder.catatan.text = currentitem.catatan
        holder.harga.text = currentitem.harga
        holder.kategori.text = currentitem.kategori
        holder.pengguna.text = currentitem.pengguna
    }

    override fun getItemCount(): Int {

        return riwayatList.size
    }


}