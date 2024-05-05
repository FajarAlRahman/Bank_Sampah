package com.example.banksampah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.banksampah.databinding.ActivityTakeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TakeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTakeBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnKirim.setOnClickListener {
            databaseReference = FirebaseDatabase.getInstance("https://banksampah-933df-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("BankSampah")

            val userId = databaseReference.push().key!!
            val pengguna = binding.namaPengguna.text.toString()
            val kategori = binding.kategorisampah.isActivated.toString()
            val berat = binding.beratsampah.text.toString()
            val harga = binding.hargaSampah.text.toString()
            val alamat = binding.alamat.text.toString()
            val catatan = binding.textNote.text.toString()

                val ambilData = AmbilData(userId, pengguna, kategori, berat, harga, alamat, catatan)
                databaseReference.child(userId).setValue(ambilData).addOnSuccessListener {
                    binding.namaPengguna.text.clear()
                    binding.beratsampah.text.clear()
                    binding.hargaSampah.text.clear()
                    binding.alamat.text.clear()
                    binding.textNote.text.clear()

                    Toast.makeText(this, "Sampah Anda dalam proses pengampilan", Toast.LENGTH_SHORT).show()

                    val intentMain = Intent(this, MainActivity::class.java)
                    startActivity(intentMain)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, "Pengiriman Anda gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }
}