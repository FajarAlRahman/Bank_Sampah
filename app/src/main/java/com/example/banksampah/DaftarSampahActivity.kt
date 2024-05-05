package com.example.banksampah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banksampah.databinding.ActivityDaftarSampahBinding

class DaftarSampahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDaftarSampahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarSampahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}