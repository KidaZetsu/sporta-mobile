package com.example.sporta.Latihan

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sporta.R
import com.example.sporta.databinding.ItemProgramBinding

private object SelisihProgram : DiffUtil.ItemCallback<ProgramModel>() {
    override fun areItemsTheSame(lama: ProgramModel, baru: ProgramModel): Boolean =
        lama.nama == baru.nama

    // diikuti berubah -> isi berbeda -> kartu ini digambar ulang
    override fun areContentsTheSame(lama: ProgramModel, baru: ProgramModel): Boolean =
        lama == baru
}

class ProgramAdapter(
    private val onTombolClick: (ProgramModel) -> Unit
) : ListAdapter<ProgramModel, ProgramAdapter.ProgramViewHolder>(SelisihProgram) {

    inner class ProgramViewHolder(val binding: ItemProgramBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val binding = ItemProgramBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProgramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        val program = getItem(position)
        val context = holder.itemView.context

        with(holder.binding) {
            tvNama.text = program.nama
            tvInfo.text = "${program.durasi} · ${program.jumlahGerakan} gerakan"
            tvDeskripsi.text = program.deskripsi
            tvLevel.text = program.level

            // Warna level dipakai di teks level dan garis kiri kartu
            val warnaLevel = ContextCompat.getColor(
                context,
                when (program.level) {
                    "Pemula" -> R.color.level_pemula
                    "Menengah" -> R.color.level_menengah
                    else -> R.color.level_mahir
                }
            )
            tvLevel.setTextColor(warnaLevel)
            viewStrip.setBackgroundColor(warnaLevel)

            // Tampilan tombol mengikuti status diikuti
            if (program.diikuti) {
                btnMulai.text = "Sedang diikuti"
                btnMulai.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.sporta_charcoal)
                )
            } else {
                btnMulai.text = "Mulai"
                btnMulai.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.sporta_red)
                )
            }

            btnMulai.setOnClickListener { onTombolClick(program) }
        }
    }
}