package com.example.sporta

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.sporta.databinding.ActivitySplashScreenBinding
import com.example.sporta.utils.SessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val session = SessionManager(this)

        lifecycleScope.launch {
            delay(2000) // simulasi pengambilan data selama 2 detik

            val target = if (session.isLoggedIn()) {
                MainActivity::class.java
            } else {
                LoginActivity::class.java
            }

            startActivity(Intent(this@SplashScreenActivity, target))
            finish()



        }
    }
}