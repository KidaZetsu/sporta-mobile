package com.example.sporta

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.sporta.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Ambil data kiriman dari GerakanFragment
        val nama = intent.getStringExtra("nama") ?: ""
        val otot = intent.getStringExtra("otot") ?: ""
        val setRep = intent.getStringExtra("setRep") ?: ""
        val level = intent.getStringExtra("level") ?: ""
        val gambar = intent.getIntExtra("gambar", R.drawable.logo_sporta)
        val deskripsi = intent.getStringExtra("deskripsi") ?: ""

        // Toolbar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = nama
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        // Tampilkan data
        binding.tvDetailNama.text = nama
        binding.tvDetailLevel.text = level
        binding.tvDetailOtot.text = "Otot: $otot"
        binding.tvDetailSetRep.text = "Target: $setRep"
        binding.tvDetailDeskripsi.text = deskripsi

        val warna = when (level) {
            "Pemula" -> R.color.level_pemula
            "Menengah" -> R.color.level_menengah
            else -> R.color.level_mahir
        }
        binding.tvDetailLevel.setTextColor(ContextCompat.getColor(this, warna))

        Glide.with(this)
            .load(gambar)
            .into(binding.imgDetail)

        // Tombol panduan -> WebViewActivity
        binding.btnPanduan.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", "https://www.google.com/search?q=cara+melakukan+$nama")
            intent.putExtra("judul", "Panduan $nama")
            startActivity(intent)
        }

        binding.imgDetail.setOnClickListener {
            com.google.android.material.snackbar.Snackbar
                .make(binding.root, "Semangat latihan $nama, Spartan!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    // Tombol back di Toolbar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}