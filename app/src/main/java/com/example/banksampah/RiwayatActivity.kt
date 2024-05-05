package com.example.banksampah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.banksampah.databinding.ActivityRiwayatBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RiwayatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var riwayatRecyclerView: RecyclerView
    private lateinit var riwayatArrayList: ArrayList<RiwayatData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        riwayatArrayList = arrayListOf<RiwayatData>()
        riwayatRecyclerView = findViewById(R.id.riwayatList)
        riwayatRecyclerView.layoutManager = LinearLayoutManager(this)
        riwayatRecyclerView.setHasFixedSize(true)
        riwayatRecyclerView.adapter = RiwayatAdapter(riwayatArrayList)
        getRiwayatData()

    }

    private fun getRiwayatData() {
        databaseReference = FirebaseDatabase.getInstance("https://banksampah-933df-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference("BankSampah")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("FirebaseData", "Data snapshot: $snapshot")
                if (snapshot.exists()) {
                    try {
                        // Bersihkan array sebelum menambahkan data baru
                        riwayatArrayList.clear()

                        for (riwayatSnapshot in snapshot.children) {
                            val alamat = riwayatSnapshot.child("alamat").getValue(String::class.java)
                            val berat = riwayatSnapshot.child("berat").getValue(String::class.java)
                            val catatan = riwayatSnapshot.child("catatan").getValue(String::class.java)
                            val harga = riwayatSnapshot.child("harga").getValue(String::class.java)
                            val kategori = riwayatSnapshot.child("kategori").getValue(String::class.java)
                            val pengguna = riwayatSnapshot.child("pengguna").getValue(String::class.java)

                            val riwayat = RiwayatData(alamat, berat, catatan, harga, kategori, pengguna)
                            Log.d("FirebaseData", "Riwayat: $riwayat")
                            riwayatArrayList.add(riwayat)
                        }

                        // Refresh RecyclerView setelah menambahkan data baru
                        riwayatRecyclerView.adapter?.notifyDataSetChanged()
                    } catch (e: Exception) {
                        Log.e("FirebaseData", "Error parsing data: ${e.message}")
                    }
                } else {
                    Log.d("FirebaseData", "No data available.")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error: ${error.message}")
            }
        })
    }

}

