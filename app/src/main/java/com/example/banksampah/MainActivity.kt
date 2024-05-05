package com.example.banksampah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banksampah.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.locationBank.setOnClickListener {// Menghubungkan ke Lokasi Bank Sampah
            val intentLocation = Intent(this, MapActivity::class.java)
            startActivity(intentLocation)
            finish()
        }

        binding.btnAmbilSampah.setOnClickListener {// Menghubungkan ke take activity/ambil sampah
            val intentTakeActivity = Intent(this, TakeActivity::class.java)
            startActivity(intentTakeActivity)
            finish()
        }

        binding.btnDaftarSampah.setOnClickListener {// Menghubungkan ke daftar sampah
            val intenDaftarSampahActivity = Intent(this, DaftarSampahActivity::class.java)
            startActivity(intenDaftarSampahActivity)
            finish()
        }

        binding.btnRiwayat.setOnClickListener {// Menghubungkan ke riwayat
            val intenRiwayatActivity = Intent(this, RiwayatActivity::class.java)
            startActivity(intenRiwayatActivity)
            finish()
        }
    }
}