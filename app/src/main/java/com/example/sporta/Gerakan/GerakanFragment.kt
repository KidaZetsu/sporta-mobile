package com.example.sporta.Gerakan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sporta.DetailActivity
import com.example.sporta.R
import com.example.sporta.databinding.FragmentGerakanBinding
import com.google.android.material.snackbar.Snackbar

class GerakanFragment : Fragment() {

    private var _binding: FragmentGerakanBinding? = null
    private val binding get() = _binding!!

    private val daftarGerakan = listOf(
        GerakanModel(
            "Push Up Klasik", "Dada", "3 set × 12 rep", "Pemula", R.drawable.gerakan_pushup,
            "Posisi tubuh lurus dengan tangan selebar bahu. Turunkan dada mendekati lantai, lalu dorong kembali ke atas. Jaga perut tetap kencang."
        ),
        GerakanModel(
            "Diamond Push Up", "Dada, Lengan", "3 set × 10 rep", "Menengah", R.drawable.gerakan_diamond,
            "Letakkan kedua tangan membentuk bentuk berlian di bawah dada. Variasi push up ini lebih menekan otot trisep."
        ),
        GerakanModel(
            "Plank", "Perut", "3 set × 45 detik", "Pemula", R.drawable.gerakan_plank,
            "Tahan tubuh lurus bertumpu pada siku dan ujung kaki. Jangan biarkan pinggul turun atau terangkat terlalu tinggi."
        ),
        GerakanModel(
            "Pistol Squat", "Kaki", "3 set × 6 rep", "Mahir", R.drawable.gerakan_pistol,
            "Squat satu kaki dengan kaki lainnya lurus ke depan. Butuh kekuatan kaki, keseimbangan, dan fleksibilitas yang baik."
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGerakanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Gerakan"

        // Custom adapter
        val adapter = GerakanAdapter(requireContext(), daftarGerakan)
        binding.listViewGerakan.adapter = adapter

        // Klik item -> Snackbar (nanti diganti pindah ke halaman detail di Bagian E)
        // Klik item -> pindah ke DetailActivity membawa data
        binding.listViewGerakan.setOnItemClickListener { _, _, position, _ ->
            val gerakan = daftarGerakan[position]
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("nama", gerakan.nama)
            intent.putExtra("otot", gerakan.otot)
            intent.putExtra("setRep", gerakan.setRep)
            intent.putExtra("level", gerakan.level)
            intent.putExtra("gambar", gerakan.gambar)
            intent.putExtra("deskripsi", gerakan.deskripsi)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}