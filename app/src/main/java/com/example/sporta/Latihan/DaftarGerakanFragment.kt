package com.example.sporta.Latihan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sporta.DetailActivity
import com.example.sporta.Gerakan.GerakanModel
import com.example.sporta.R
import com.example.sporta.databinding.FragmentDaftarGerakanBinding
import com.google.android.material.chip.Chip

class DaftarGerakanFragment : Fragment(R.layout.fragment_daftar_gerakan) {

    private var _binding: FragmentDaftarGerakanBinding? = null
    private val binding get() = _binding!!

    private lateinit var gerakanAdapter: GerakanGridAdapter

    // Dua kondisi filter yang sedang aktif
    private var kataKunci = ""
    private var levelDipilih = "Semua"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDaftarGerakanBinding.bind(view)

        // 1. Adapter dengan aksi saat kartu diklik
        gerakanAdapter = GerakanGridAdapter { gerakan -> bukaDetail(gerakan) }

        // 2. RecyclerView butuh LayoutManager DAN adapter
        binding.rvGerakan.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = gerakanAdapter
        }

        // 3. Pencarian: setiap teks berubah, filter ulang
        binding.etCari.doAfterTextChanged { teks ->
            kataKunci = teks?.toString().orEmpty().trim()
            terapkanFilter()
        }

        // 4. Filter level lewat Chip
        binding.chipGroupLevel.setOnCheckedStateChangeListener { group, checkedIds ->
            levelDipilih = checkedIds.firstOrNull()
                ?.let { group.findViewById<Chip>(it).text.toString() }
                ?: "Semua"
            terapkanFilter()
        }

        // 5. Tampilkan data awal
        terapkanFilter()
    }

    // Gabungkan filter level dan kata kunci, lalu kirim hasilnya ke adapter
    private fun terapkanFilter() {
        val hasil = GerakanData.semua.filter { gerakan ->
            val cocokLevel = levelDipilih == "Semua" || gerakan.level == levelDipilih
            val cocokKata = gerakan.nama.contains(kataKunci, ignoreCase = true) ||
                    gerakan.otot.contains(kataKunci, ignoreCase = true)
            cocokLevel && cocokKata
        }

        gerakanAdapter.submitList(hasil)
        binding.tvJumlah.text = "${hasil.size} gerakan"
        binding.tvKosong.visibility = if (hasil.isEmpty()) View.VISIBLE else View.GONE
    }

    // Pindah ke DetailActivity yang sudah ada, bawa data gerakan
    private fun bukaDetail(gerakan: GerakanModel) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra("nama", gerakan.nama)
            putExtra("otot", gerakan.otot)
            putExtra("setRep", gerakan.setRep)
            putExtra("level", gerakan.level)
            putExtra("gambar", gerakan.gambar)
            putExtra("deskripsi", gerakan.deskripsi)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}