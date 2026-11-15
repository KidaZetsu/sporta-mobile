package com.example.sporta.Latihan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sporta.R
import com.example.sporta.databinding.FragmentProgramLatihanBinding
import com.google.android.material.snackbar.Snackbar

class ProgramLatihanFragment : Fragment(R.layout.fragment_program_latihan) {

    private var _binding: FragmentProgramLatihanBinding? = null
    private val binding get() = _binding!!

    private lateinit var programAdapter: ProgramAdapter

    // Daftar program yang sedang berlaku (status diikuti ada di sini)
    private var daftarProgram = ProgramData.semua

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProgramLatihanBinding.bind(view)

        programAdapter = ProgramAdapter { program -> ubahStatus(program) }

        // Berbeda dari tab Gerakan: LinearLayoutManager = daftar vertikal 1 kolom
        binding.rvProgram.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = programAdapter
        }

        tampilkanDaftar()
    }

    // Balik status diikuti pada program yang dipilih
    private fun ubahStatus(program: ProgramModel) {
        // copy() membuat objek baru dengan satu field berbeda,
        // supaya DiffUtil bisa mendeteksi perubahan
        daftarProgram = daftarProgram.map {
            if (it.nama == program.nama) it.copy(diikuti = !it.diikuti) else it
        }
        tampilkanDaftar()

        val pesan = if (!program.diikuti) {
            "Program ${program.nama} dimulai"
        } else {
            "Program ${program.nama} dihentikan"
        }
        Snackbar.make(binding.root, pesan, Snackbar.LENGTH_SHORT).show()
    }

    private fun tampilkanDaftar() {
        programAdapter.submitList(daftarProgram)
        val jumlahDiikuti = daftarProgram.count { it.diikuti }
        binding.tvRingkasan.text = "$jumlahDiikuti dari ${daftarProgram.size} program diikuti"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}