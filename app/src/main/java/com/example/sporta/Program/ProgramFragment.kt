package com.example.sporta.Program

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sporta.databinding.FragmentProgramBinding

class ProgramFragment : Fragment() {

    private var _binding: FragmentProgramBinding? = null
    private val binding get() = _binding!!

    // Data program: setiap item punya key "title" dan "desc"
    private val dataListWithDesc = listOf(
        mapOf("title" to "Spartan 30 Hari", "desc" to "30 hari · Menengah · 5x seminggu · PREMIUM"),
        mapOf("title" to "Pemula 14 Hari", "desc" to "14 hari · Pemula · 3x seminggu · GRATIS"),
        mapOf("title" to "Core Kuat 21 Hari", "desc" to "21 hari · Menengah · 4x seminggu · PREMIUM"),
        mapOf("title" to "Full Body 7 Hari", "desc" to "7 hari · Pemula · Setiap hari · GRATIS"),
        mapOf("title" to "Push Up Master 30 Hari", "desc" to "30 hari · Mahir · 5x seminggu · PREMIUM"),
        mapOf("title" to "Kaki Baja 14 Hari", "desc" to "14 hari · Menengah · 4x seminggu · GRATIS"),
        mapOf("title" to "Fleksibilitas 10 Hari", "desc" to "10 hari · Pemula · Setiap hari · GRATIS"),
        mapOf("title" to "Hilang Lemak 28 Hari", "desc" to "28 hari · Menengah · 5x seminggu · PREMIUM")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Program"

        // Adapter: penghubung dataListWithDesc dengan layout simple_list_item_2
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),                           // key yang diambil dari data
            intArrayOf(android.R.id.text1, android.R.id.text2)  // tempat tampilnya
        )

        // Hubungkan ListView dengan adapter
        binding.listViewProgram.adapter = adapter

        // Aksi saat item diklik
        binding.listViewProgram.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(requireContext(), "Kamu memilih: $title ($desc)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}