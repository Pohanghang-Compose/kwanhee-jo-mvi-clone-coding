package com.koreatech.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class ConfigRepository(private val context: Context) {
    val preferences: SharedPreferences
        get() = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
    var createdDatabase: Boolean = false
        get() {
            return preferences.getBoolean("CREATED_DATABASE", false)
        }
        set(value) {
            preferences.edit {
                putBoolean("CREATED_DATABASE", value)
            }
            field = value
        }
}