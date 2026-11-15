package com.example.sporta.Latihan

data class ProgramModel(
    val nama: String,
    val durasi: String,
    val level: String,
    val jumlahGerakan: Int,
    val deskripsi: String,
    val diikuti: Boolean = false
)

// Sumber data program latihan
object ProgramData {

    val semua = listOf(
        ProgramModel(
            "Spartan Pemula", "7 hari · 15 menit per hari", "Pemula", 6,
            "Pengenalan gerakan dasar: push up lutut, squat, plank, dan lunge. Cocok untuk memulai kebiasaan latihan."
        ),
        ProgramModel(
            "Peregangan Pagi", "7 hari · 10 menit per hari", "Pemula", 5,
            "Rutinitas ringan setiap pagi untuk melemaskan badan dan mengurangi kaku sebelum beraktivitas."
        ),
        ProgramModel(
            "Dada dan Lengan Kuat", "14 hari · 20 menit per hari", "Menengah", 5,
            "Variasi push up dari klasik sampai diamond untuk membentuk dada, bahu, dan trisep."
        ),
        ProgramModel(
            "Perut Baja", "10 hari · 15 menit per hari", "Menengah", 4,
            "Kombinasi plank dan variasinya untuk menguatkan otot inti dan perut."
        ),
        ProgramModel(
            "Kaki Spartan", "14 hari · 25 menit per hari", "Menengah", 5,
            "Squat, lunge, dan jump squat bergantian untuk kaki yang kuat dan seimbang."
        ),
        ProgramModel(
            "Full Body Mahir", "30 hari · 40 menit per hari", "Mahir", 10,
            "Program lengkap seluruh tubuh untuk yang sudah terbiasa, termasuk pistol squat dan archer push up."
        )
    )
}