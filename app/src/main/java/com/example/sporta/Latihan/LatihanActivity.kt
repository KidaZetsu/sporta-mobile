package com.example.sporta.Latihan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sporta.R
import com.example.sporta.databinding.ActivityLatihanBinding
import com.google.android.material.tabs.TabLayoutMediator

class LatihanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLatihanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatihanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Toolbar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pusat Latihan"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // 1. Adapter dipasang dulu ke ViewPager2
        val tabsAdapter = LatihanTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // 2. Baru TabLayout dihubungkan, judul dan ikon diambil dari daftarTab
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val info = tabsAdapter.daftarTab[position]
            tab.text = info.judul
            tab.icon = ContextCompat.getDrawable(this, info.ikon)
        }.attach()

        // 3. Badge di tab
        pasangBadge()
    }

    private fun pasangBadge() {
        // Setiap tab diberi badge angka sesuai jumlah isinya
        val jumlah = listOf(
            GerakanData.semua.size,
            ProgramData.semua.size,
            daftarTips.size
        )
        jumlah.forEachIndexed { index, angka ->
            binding.tabLayout.getTabAt(index)?.getOrCreateBadge()?.apply {
                isVisible = true
                number = angka
            }
        }
    }

    // Tombol back di Toolbar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}