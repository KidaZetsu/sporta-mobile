package com.example.sporta.Progress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sporta.data.entity.RiwayatEntity
import com.example.sporta.databinding.ItemRiwayatBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private object SelisihRiwayat : DiffUtil.ItemCallback<RiwayatEntity>() {
    // Catatan yang sama = id yang sama
    override fun areItemsTheSame(lama: RiwayatEntity, baru: RiwayatEntity): Boolean =
        lama.id == baru.id

    override fun areContentsTheSame(lama: RiwayatEntity, baru: RiwayatEntity): Boolean =
        lama == baru
}

class RiwayatAdapter(
    private val onHapusClick: (RiwayatEntity) -> Unit,
    private val onItemClick: (RiwayatEntity) -> Unit = {}
) : ListAdapter<RiwayatEntity, RiwayatAdapter.RiwayatViewHolder>(SelisihRiwayat) {

    // Format tanggal, dibuat sekali saja
    private val formatTanggal = SimpleDateFormat("dd MMM yyyy, HH.mm", Locale("id", "ID"))

    inner class RiwayatViewHolder(val binding: ItemRiwayatBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val binding = ItemRiwayatBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RiwayatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        val riwayat = getItem(position)

        with(holder.binding) {
            tvNama.text = riwayat.namaGerakan
            tvTarget.text = "${riwayat.jumlahSet} set × ${riwayat.jumlahRep} rep"
            tvTanggal.text = formatTanggal.format(Date(riwayat.tanggal))

            // Catatan opsional: disembunyikan kalau kosong
            tvCatatan.text = riwayat.catatan
            tvCatatan.visibility = if (riwayat.catatan.isBlank()) View.GONE else View.VISIBLE

            btnHapus.setOnClickListener { onHapusClick(riwayat) }
            root.setOnClickListener { onItemClick(riwayat) }
        }
    }
}