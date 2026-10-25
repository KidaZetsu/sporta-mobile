package com.example.sporta.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveLogin(username: String, isGuest: Boolean = false) {
        prefs.edit()
            .putBoolean(KEY_IS_LOGIN, true)
            .putString(KEY_USERNAME, username)
            .putBoolean(KEY_IS_GUEST, isGuest)
            .apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGIN, false)

    fun getUsername(): String = prefs.getString(KEY_USERNAME, "Pengguna") ?: "Pengguna"

    fun isGuest(): Boolean = prefs.getBoolean(KEY_IS_GUEST, false)

    fun logout() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "sporta_pref"
        private const val KEY_IS_LOGIN = "isLogin"
        private const val KEY_USERNAME = "username"
        private const val KEY_IS_GUEST = "isGuest"
    }
}