package com.example.sporta.Latihan

import com.example.sporta.Gerakan.GerakanModel
import com.example.sporta.R

// Sumber data gerakan untuk Pusat Latihan
object GerakanData {

    val semua = listOf(
        GerakanModel(
            "Push Up Klasik", "Dada", "3 set × 12 rep", "Pemula", R.drawable.gerakan_pushup,
            "Posisi tubuh lurus dengan tangan selebar bahu. Turunkan dada mendekati lantai, lalu dorong kembali ke atas. Jaga perut tetap kencang."
        ),
        GerakanModel(
            "Diamond Push Up", "Dada, Lengan", "3 set × 10 rep", "Menengah", R.drawable.gerakan_diamond,
            "Letakkan kedua tangan membentuk bentuk berlian di bawah dada. Variasi push up ini lebih menekan otot trisep."
        ),
        GerakanModel(
            "Plank", "Perut", "3 set × 45 detik", "Pemula", R.drawable.gerakan_plank,
            "Tahan tubuh lurus bertumpu pada siku dan ujung kaki. Jangan biarkan pinggul turun atau terangkat terlalu tinggi."
        ),
        GerakanModel(
            "Pistol Squat", "Kaki", "3 set × 6 rep", "Mahir", R.drawable.gerakan_pistol,
            "Squat satu kaki dengan kaki lainnya lurus ke depan. Butuh kekuatan kaki, keseimbangan, dan fleksibilitas yang baik."
        ),
        GerakanModel(
            "Push Up Lutut", "Dada", "3 set × 10 rep", "Pemula", R.drawable.gerakan_pushup,
            "Versi push up yang lebih ringan dengan lutut menempel di lantai. Cocok untuk membangun kekuatan sebelum push up klasik."
        ),
        GerakanModel(
            "Side Plank", "Perut", "3 set × 30 detik", "Menengah", R.drawable.gerakan_plank,
            "Bertumpu pada satu siku dengan tubuh miring lurus dari kepala sampai kaki. Melatih otot perut samping dan keseimbangan."
        ),
        GerakanModel(
            "Squat", "Kaki", "3 set × 15 rep", "Pemula", R.drawable.gerakan_pistol,
            "Berdiri selebar bahu, turunkan pinggul seperti duduk di kursi, lalu berdiri kembali. Punggung tetap lurus dan lutut tidak melewati ujung kaki."
        ),
        GerakanModel(
            "Jump Squat", "Kaki", "3 set × 12 rep", "Menengah", R.drawable.gerakan_pistol,
            "Squat yang diakhiri lompatan ke atas. Mendarat dengan lembut dan langsung lanjut ke repetisi berikutnya."
        ),
        GerakanModel(
            "Lunge", "Kaki", "3 set × 10 rep", "Pemula", R.drawable.gerakan_pistol,
            "Melangkah ke depan lalu turunkan lutut belakang mendekati lantai. Dorong kembali ke posisi berdiri dan ganti kaki."
        ),
        GerakanModel(
            "Archer Push Up", "Dada, Bahu", "3 set × 6 rep", "Mahir", R.drawable.gerakan_diamond,
            "Tangan lebar, turunkan tubuh ke satu sisi sementara lengan sisi lain tetap lurus. Melatih kekuatan satu lengan secara bergantian."
        ),
        GerakanModel(
            "Plank Angkat Kaki", "Perut, Bokong", "3 set × 10 rep", "Menengah", R.drawable.gerakan_plank,
            "Posisi plank, lalu angkat satu kaki bergantian tanpa menggoyangkan pinggul. Perut tetap kencang selama gerakan."
        ),
        GerakanModel(
            "Pike Push Up", "Bahu", "3 set × 8 rep", "Menengah", R.drawable.gerakan_pushup,
            "Pinggul diangkat tinggi membentuk huruf V terbalik, lalu turunkan kepala ke arah lantai. Fokus ke otot bahu."
        )
    )
}