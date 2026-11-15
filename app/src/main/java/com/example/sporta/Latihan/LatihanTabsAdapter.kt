package com.example.sporta.Latihan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sporta.R

// Satu baris data = satu tab: judul, ikon, dan cara membuat Fragment-nya
data class TabInfo(
    val judul: String,
    val ikon: Int,
    val buatFragment: () -> Fragment
)

class LatihanTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Daftar tab disimpan di satu tempat saja
    val daftarTab = listOf(
        TabInfo("Gerakan", R.drawable.ic_gerakan) { DaftarGerakanFragment() },
        TabInfo("Program", R.drawable.ic_program) { ProgramLatihanFragment() },
        TabInfo("Tips", R.drawable.ic_info) { TipsFragment() }
    )

    // Jumlah tab otomatis mengikuti isi daftarTab
    override fun getItemCount(): Int = daftarTab.size

    // Fragment dibuat dari data tab pada posisi tersebut
    override fun createFragment(position: Int): Fragment = daftarTab[position].buatFragment()
}