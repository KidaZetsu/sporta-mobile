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

    companion object {
        // Kunci Intent untuk id catatan yang akan diubah
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityRiwayatFormBinding
    private lateinit var db: AppDatabase

    // -1 berarti mode tambah, selain itu mode ubah
    private var idUbah = -1
    // Catatan lama yang dimuat dari database (hanya terisi di mode ubah)
    private var riwayatLama: RiwayatEntity? = null

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

        // Kalau ada id di Intent, berarti mode ubah
        idUbah = intent.getIntExtra(EXTRA_ID, -1)
        if (idUbah != -1) siapkanModeUbah(idUbah)

        binding.btnSimpan.setOnClickListener { simpan() }
    }

    // Mode ubah: ganti teks, lalu isi kolom dengan data lama dari database
    private fun siapkanModeUbah(id: Int) {
        supportActionBar?.title = "Ubah Catatan"
        binding.tvJudulForm.text = "Ubah sesi latihanmu"
        binding.btnSimpan.text = "Simpan Perubahan"

        lifecycleScope.launch {
            val data = db.riwayatDao().cariById(id) ?: return@launch
            riwayatLama = data
            // false = jangan memfilter dropdown, supaya semua pilihan tetap muncul
            binding.actGerakan.setText(data.namaGerakan, false)
            binding.etSet.setText(data.jumlahSet.toString())
            binding.etRep.setText(data.jumlahRep.toString())
            binding.etCatatan.setText(data.catatan)
        }
    }

    private fun simpan() {
        // Mode ubah tapi data lama belum selesai dimuat: tunggu dulu
        if (idUbah != -1 && riwayatLama == null) return

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

        binding.btnSimpan.isEnabled = false
        lifecycleScope.launch {
            try {
                val lama = riwayatLama
                if (lama == null) {
                    // Mode tambah: baris baru
                    db.riwayatDao().insert(
                        RiwayatEntity(
                            namaGerakan = gerakan,
                            jumlahSet = jumlahSet,
                            jumlahRep = jumlahRep,
                            catatan = catatan
                        )
                    )
                    Toast.makeText(this@RiwayatFormActivity, "Catatan tersimpan", Toast.LENGTH_SHORT).show()
                } else {
                    // Mode ubah: salin data lama (id dan tanggal tetap), ganti isinya
                    db.riwayatDao().update(
                        lama.copy(
                            namaGerakan = gerakan,
                            jumlahSet = jumlahSet,
                            jumlahRep = jumlahRep,
                            catatan = catatan
                        )
                    )
                    Toast.makeText(this@RiwayatFormActivity, "Catatan diperbarui", Toast.LENGTH_SHORT).show()
                }
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