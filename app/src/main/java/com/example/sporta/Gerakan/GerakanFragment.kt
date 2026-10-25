package com.example.sporta.Gerakan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sporta.R
import com.example.sporta.databinding.FragmentGerakanBinding
import com.google.android.material.snackbar.Snackbar

class GerakanFragment : Fragment() {

    private var _binding: FragmentGerakanBinding? = null
    private val binding get() = _binding!!

    private val daftarGerakan = listOf(
        GerakanModel("Push Up Klasik", "Dada", "3 set × 12 rep", "Pemula", R.drawable.gerakan_pushup),
        GerakanModel("Diamond Push Up", "Dada, Lengan", "3 set × 10 rep", "Menengah", R.drawable.gerakan_diamond),
        GerakanModel("Plank", "Perut", "3 set × 45 detik", "Pemula", R.drawable.gerakan_plank),
        GerakanModel("Pistol Squat", "Kaki", "3 set × 6 rep", "Mahir", R.drawable.gerakan_pistol)
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
        binding.listViewGerakan.setOnItemClickListener { _, _, position, _ ->
            val gerakan = daftarGerakan[position]
            Snackbar.make(binding.root, "Kamu memilih: ${gerakan.nama}", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}