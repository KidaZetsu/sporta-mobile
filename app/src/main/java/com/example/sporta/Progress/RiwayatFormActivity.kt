package com.example.sporta.Progress

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.sporta.Latihan.GerakanData
import com.example.sporta.R
import com.example.sporta.data.AppDatabase
import com.example.sporta.data.entity.RiwayatEntity
import com.example.sporta.databinding.ActivityRiwayatFormBinding
import kotlinx.coroutines.launch

class RiwayatFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = AppDatabase.getInstance(this)

        // Toolbar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Catat Latihan"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // Dropdown gerakan diambil dari data Pusat Latihan
        val namaGerakan = GerakanData.semua.map { it.nama }
        binding.actGerakan.setAdapter(
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, namaGerakan)
        )

        binding.btnSimpan.setOnClickListener { simpan() }
    }

    private fun simpan() {
        val gerakan = binding.actGerakan.text.toString().trim()
        val jumlahSet = binding.etSet.text.toString().toIntOrNull()
        val jumlahRep = binding.etRep.text.toString().toIntOrNull()
        val catatan = binding.etCatatan.text.toString().trim()

        // Validasi: tampilkan pesan di bawah kolom yang bermasalah
        binding.tilGerakan.error = if (gerakan.isEmpty()) "Pilih gerakan dulu" else null
        binding.tilSet.error = if (jumlahSet == null || jumlahSet < 1) "Minimal 1" else null
        binding.tilRep.error = if (jumlahRep == null || jumlahRep < 1) "Minimal 1" else null

        if (gerakan.isEmpty() || jumlahSet == null || jumlahSet < 1 ||
            jumlahRep == null || jumlahRep < 1
        ) return

        // Simpan lewat coroutine, karena insert() adalah fungsi suspend
        binding.btnSimpan.isEnabled = false
        lifecycleScope.launch {
            try {
                db.riwayatDao().insert(
                    RiwayatEntity(
                        namaGerakan = gerakan,
                        jumlahSet = jumlahSet,
                        jumlahRep = jumlahRep,
                        catatan = catatan
                    )
                )
                Toast.makeText(this@RiwayatFormActivity, "Catatan tersimpan", Toast.LENGTH_SHORT).show()
                finish()
            } catch (e: Exception) {
                binding.btnSimpan.isEnabled = true
                Toast.makeText(this@RiwayatFormActivity, "Gagal menyimpan catatan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Tombol back di Toolbar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}