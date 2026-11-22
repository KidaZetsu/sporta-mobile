package com.example.sporta.Progress

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sporta.R
import com.example.sporta.data.AppDatabase
import com.example.sporta.data.entity.RiwayatEntity
import com.example.sporta.databinding.FragmentProgressBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class ProgressFragment : Fragment(R.layout.fragment_progress) {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var riwayatAdapter: RiwayatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProgressBinding.bind(view)

        // Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Progress"

        db = AppDatabase.getInstance(requireContext())

        // Adapter: aksi hapus dikirim ke fungsi konfirmasiHapus
        riwayatAdapter = RiwayatAdapter(onHapusClick = { konfirmasiHapus(it) })

        binding.rvRiwayat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = riwayatAdapter
        }

        // Tombol + membuka form
        binding.fabTambah.setOnClickListener {
            startActivity(Intent(requireContext(), RiwayatFormActivity::class.java))
        }

        amatiDatabase()
    }

    // Mendengarkan Flow dari DAO: setiap tabel berubah, daftar ikut berubah
    private fun amatiDatabase() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                db.riwayatDao().semuaRiwayat().collect { daftar ->
                    riwayatAdapter.submitList(daftar)
                    tampilkanRingkasan(daftar)
                }
            }
        }
    }

    private fun tampilkanRingkasan(daftar: List<RiwayatEntity>) {
        if (daftar.isEmpty()) {
            binding.tvKosong.visibility = View.VISIBLE
            binding.tvRingkasan.text = "Belum ada sesi tercatat"
        } else {
            binding.tvKosong.visibility = View.GONE
            val totalRep = daftar.sumOf { it.jumlahSet * it.jumlahRep }
            binding.tvRingkasan.text = "${daftar.size} sesi tercatat · $totalRep repetisi total"
        }
    }

    // Dialog konfirmasi sebelum menghapus
    private fun konfirmasiHapus(riwayat: RiwayatEntity) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Hapus Catatan")
            .setMessage("Yakin ingin menghapus catatan ${riwayat.namaGerakan}?")
            .setPositiveButton("Ya") { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    db.riwayatDao().delete(riwayat)
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}