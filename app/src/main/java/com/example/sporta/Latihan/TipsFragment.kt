package com.example.sporta.Latihan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.sporta.R
import com.example.sporta.databinding.FragmentTipsBinding
import com.example.sporta.databinding.ItemTipBinding

data class TipModel(val judul: String, val isi: String)

// Data tips, dipakai juga untuk angka badge di tab
val daftarTips = listOf(
    TipModel("Pemanasan dulu", "Lakukan pemanasan 5 menit sebelum latihan: putar bahu, putar pinggul, dan peregangan ringan."),
    TipModel("Atur napasmu", "Hembuskan napas saat mendorong atau mengangkat, dan tarik napas saat kembali ke posisi awal."),
    TipModel("Bentuk di atas jumlah", "Gerakan yang benar dengan repetisi sedikit lebih baik daripada banyak tetapi asal-asalan."),
    TipModel("Beri jeda antar set", "Istirahat 30 sampai 60 detik di antara set supaya tenaga pulih dan bentuk gerakan tetap terjaga."),
    TipModel("Cukupi minum", "Minum air putih sebelum, saat, dan sesudah latihan agar tubuh tidak mudah lelah.")
)

class TipsFragment : Fragment(R.layout.fragment_tips) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTipsBinding.bind(view)

        // Satu kartu dibuat untuk setiap tips, lalu dimasukkan ke container
        daftarTips.forEachIndexed { index, tip ->
            val kartu = ItemTipBinding.inflate(layoutInflater, binding.containerTips, false)
            kartu.tvNomor.text = (index + 1).toString()
            kartu.tvJudulTip.text = tip.judul
            kartu.tvIsiTip.text = tip.isi
            binding.containerTips.addView(kartu.root)
        }
    }
}