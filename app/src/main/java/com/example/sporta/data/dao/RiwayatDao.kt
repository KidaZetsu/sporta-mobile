package com.example.sporta.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sporta.data.entity.RiwayatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RiwayatDao {

    // Membaca semua catatan, terbaru di atas. Flow: otomatis mengirim daftar baru setiap tabel berubah
    @Query("SELECT * FROM riwayat_latihan ORDER BY tanggal DESC")
    fun semuaRiwayat(): Flow<List<RiwayatEntity>>

    // Mengambil satu catatan berdasarkan id (dipakai saat mengubah catatan)
    @Query("SELECT * FROM riwayat_latihan WHERE id = :id")
    suspend fun cariById(id: Int): RiwayatEntity?

    @Insert
    suspend fun insert(riwayat: RiwayatEntity)

    @Update
    suspend fun update(riwayat: RiwayatEntity)

    @Delete
    suspend fun delete(riwayat: RiwayatEntity)
}