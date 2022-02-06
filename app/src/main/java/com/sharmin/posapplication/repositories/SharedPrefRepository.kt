package com.sharmin.posapplication.repositories

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefRepository @Inject constructor(@ApplicationContext context : Context) {

    private val sharedPref: SharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    fun store(key: String, value: String) {
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun store(key: String, value: Boolean) {
        with (sharedPref.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    fun getBoolean(key: String): Boolean? {
        if (!sharedPref.contains(key)) return null
        return sharedPref.getBoolean(key, false)
    }

    fun getString(key: String): String? {
        if (!sharedPref.contains(key)) return null
        return sharedPref.getString(key, "")
    }

    companion object {

        const val SHARED_PREF_NAME = "local_settings_storage"
        const val KEY_USERNAME = "KEY_USERNAME"
        const val KEY_PASSWORD = "KEY_PASSWORD"
        const val KEY_REMEMBER = "KEY_REMEMBER"
    }
}