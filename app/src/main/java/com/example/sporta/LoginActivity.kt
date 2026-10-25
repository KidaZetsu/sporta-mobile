package com.example.sporta

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.example.sporta.databinding.ActivityLoginBinding
import com.example.sporta.utils.SessionManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        session = SessionManager(this)

        binding.etUsername.doOnTextChanged { _, _, _, _ -> binding.tilUsername.error = null }
        binding.etPassword.doOnTextChanged { _, _, _, _ -> binding.tilPassword.error = null }

        binding.btnLogin.setOnClickListener { attemptLogin() }

        binding.etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                attemptLogin()
                true
            } else {
                false
            }
        }

        binding.btnGuest.setOnClickListener { loginAsGuest() }
    }

    private fun attemptLogin() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString()

        if (username.isEmpty()) {
            binding.tilUsername.error = "Username tidak boleh kosong"
            return
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Password tidak boleh kosong"
            return
        }

        if (username == password) {
            session.saveLogin(username)
            goToMain()
        } else {
            showLoginFailedDialog()
        }
    }

    private fun showLoginFailedDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Login Gagal")
            .setMessage("Username dan password tidak sesuai. Silahkan coba lagi.")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun loginAsGuest() {
        session.saveLogin(username = "Tamu", isGuest = true)
        goToMain()
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish() // tutup Login supaya tombol Back tidak kembali ke sini
    }
}