package com.example.sporta.Home.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sporta.databinding.ActivityTutorialHomeBinding

class TutorialHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup ViewPager dengan adapter
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialViewPager.adapter = adapter

        // Hubungkan DotsIndicator (harus SETELAH adapter dipasang)
        binding.dotIndicator.attachTo(binding.tutorialViewPager)
    }
}