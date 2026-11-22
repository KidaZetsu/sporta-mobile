package com.example.sporta.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Satu objek RiwayatEntity = satu baris pada tabel riwayat_latihan
@Entity(tableName = "riwayat_latihan")
data class RiwayatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaGerakan: String,
    val jumlahSet: Int,
    val jumlahRep: Int,
    val catatan: String = "",
    val tanggal: Long = System.currentTimeMillis()
)