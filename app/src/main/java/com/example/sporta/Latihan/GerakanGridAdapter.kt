package com.example.sporta.Latihan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sporta.Gerakan.GerakanModel
import com.example.sporta.R
import com.example.sporta.databinding.ItemGerakanGridBinding

// Aturan DiffUtil: bagaimana membandingkan item lama dan baru
private object SelisihGerakan : DiffUtil.ItemCallback<GerakanModel>() {
    // Apakah ini gerakan yang sama? (dibandingkan lewat nama)
    override fun areItemsTheSame(lama: GerakanModel, baru: GerakanModel): Boolean =
        lama.nama == baru.nama

    // Kalau sama, apakah isinya berubah? (data class membandingkan semua field)
    override fun areContentsTheSame(lama: GerakanModel, baru: GerakanModel): Boolean =
        lama == baru
}

class GerakanGridAdapter(
    private val onItemClick: (GerakanModel) -> Unit
) : ListAdapter<GerakanModel, GerakanGridAdapter.GerakanViewHolder>(SelisihGerakan) {

    // ViewHolder: memegang binding satu kartu
    inner class GerakanViewHolder(val binding: ItemGerakanGridBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Dipanggil saat RecyclerView butuh kartu baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GerakanViewHolder {
        val binding = ItemGerakanGridBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GerakanViewHolder(binding)
    }

    // Dipanggil saat kartu perlu diisi data pada posisi tertentu
    override fun onBindViewHolder(holder: GerakanViewHolder, position: Int) {
        val gerakan = getItem(position)

        with(holder.binding) {
            tvNama.text = gerakan.nama
            tvOtot.text = gerakan.otot
            tvSetRep.text = gerakan.setRep
            tvLevel.text = gerakan.level

            // Warna level mengikuti colors.xml
            val warna = when (gerakan.level) {
                "Pemula" -> R.color.level_pemula
                "Menengah" -> R.color.level_menengah
                else -> R.color.level_mahir
            }
            tvLevel.setTextColor(ContextCompat.getColor(root.context, warna))

            Glide.with(holder.itemView.context)
                .load(gerakan.gambar)
                .into(imgGerakan)

            // Laporkan klik ke luar (ke Fragment)
            root.setOnClickListener { onItemClick(gerakan) }
        }
    }
}