package com.example.sporta

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.sporta.Gerakan.GerakanFragment
import com.example.sporta.Home.HomeFragment
import com.example.sporta.Program.ProgramFragment
import com.example.sporta.Progress.ProgressFragment
import com.example.sporta.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0) // bottom = 0
            insets
        }

        // HomeFragment sebagai fragment default
        replaceFragment(HomeFragment())

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.gerakan -> {
                    replaceFragment(GerakanFragment())
                    true
                }
                R.id.progress -> {
                    replaceFragment(ProgressFragment())
                    true
                }
                R.id.program -> {
                    replaceFragment(ProgramFragment())
                    true
                }
                else -> false // false jika item tidak dikenal
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            // .addToBackStack(null) sengaja dinonaktifkan: saat Back langsung keluar aplikasi
            .commit()
    }
}