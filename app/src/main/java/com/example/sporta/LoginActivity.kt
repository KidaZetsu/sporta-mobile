package com.example.sporta

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sporta.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Tombol Masuk: pengecekan username dan password
        binding.btnLogin.setOnClickListener {
            val user = binding.edtUsername.text.toString().trim()
            val pass = binding.edtPassword.text.toString()

            if (user.isEmpty()) {
                binding.edtUsername.error = "Username tidak boleh kosong"
            } else if (pass.isEmpty()) {
                binding.edtPassword.error = "Password tidak boleh kosong"
            } else if (user == pass) {
                // Login berhasil: simpan isLogin dan username
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", user)
                editor.apply()

                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username dan password tidak sesuai. Silahkan coba lagi.")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }

        // Tombol Masuk sebagai Tamu
        binding.btnGuest.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putBoolean("isLogin", true)
            editor.putString("username", "Tamu")
            editor.apply()

            startActivity(Intent(this, BaseActivity::class.java))
            finish()
        }
    }
}