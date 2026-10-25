package com.example.sporta.Progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sporta.databinding.FragmentProgressBinding

class ProgressFragment : Fragment() {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    // Data riwayat latihan
    private val dataList = listOf(
        "Full Body Pemula · Kemarin · 20 menit",
        "Perut Kuat · 2 hari lalu · 15 menit",
        "Push Up Challenge · 3 hari lalu · 12 menit",
        "Kaki Dasar · 4 hari lalu · 18 menit",
        "Full Body Pemula · 5 hari lalu · 20 menit",
        "Stretching Pagi · 6 hari lalu · 10 menit",
        "Core Kuat · 7 hari lalu · 25 menit",
        "Bahu dan Lengan · 8 hari lalu · 20 menit",
        "Full Body Menengah · 9 hari lalu · 30 menit",
        "Pemanasan Dasar · 10 hari lalu · 8 menit"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Progress"

        // Adapter: penghubung dataList dengan layout simple_list_item_1
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            dataList
        )

        // Hubungkan ListView dengan adapter
        binding.listViewProgress.adapter = adapter

        // Aksi saat item diklik
        binding.listViewProgress.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataList[position]
            Toast.makeText(requireContext(), "Kamu memilih: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}