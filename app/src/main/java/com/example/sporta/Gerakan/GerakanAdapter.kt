package com.example.sporta.Gerakan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.sporta.R
import com.example.sporta.databinding.ItemGerakanBinding

class GerakanAdapter(
    context: Context,
    private val daftarGerakan: List<GerakanModel>
) : ArrayAdapter<GerakanModel>(context, 0, daftarGerakan) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Pakai ulang view lama kalau ada, kalau belum ada baru buat
        val binding = if (convertView == null) {
            ItemGerakanBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            ItemGerakanBinding.bind(convertView)
        }

        val gerakan = daftarGerakan[position]

        binding.tvNama.text = gerakan.nama
        binding.tvOtot.text = gerakan.otot
        binding.tvSetRep.text = gerakan.setRep
        binding.tvLevel.text = gerakan.level

        // Warna level mengikuti colors.xml
        val warna = when (gerakan.level) {
            "Pemula" -> R.color.level_pemula
            "Menengah" -> R.color.level_menengah
            else -> R.color.level_mahir
        }
        binding.tvLevel.setTextColor(ContextCompat.getColor(context, warna))

        // Glide memuat gambar dari drawable
        Glide.with(context)
            .load(gerakan.gambar)
            .into(binding.imgGerakan)

        return binding.root
    }
}