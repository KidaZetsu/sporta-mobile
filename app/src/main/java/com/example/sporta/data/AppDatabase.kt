package com.example.sporta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sporta.data.dao.RiwayatDao
import com.example.sporta.data.entity.RiwayatEntity

@Database(
    entities = [RiwayatEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun riwayatDao(): RiwayatDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Singleton: seluruh aplikasi memakai satu objek database yang sama
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sporta_database"
                ).build().also { INSTANCE = it }
            }
    }
}