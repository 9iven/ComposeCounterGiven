package com.example.countercompose

import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel

class DataDiriViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val prefs = application.getSharedPreferences(
        PREFS_NAME, Context.MODE_PRIVATE
    )

    private val _nama = mutableStateOf(prefs.getString(KEY_NAMA, "") ?: "")
    val nama: State<String> = _nama

    private val _umur = mutableStateOf(prefs.getString(KEY_UMUR, "") ?: "")
    val umur: State<String> = _umur

    fun onNamaChange(newNama: String) {
        _nama.value = newNama
        prefs.edit { putString(KEY_NAMA, newNama) }
    }

    fun onUmurChange(newUmur: String) {
        // Hanya menerima angka atau string kosong
        if (newUmur.isEmpty() || newUmur.all { it.isDigit() }) {
            _umur.value = newUmur
            prefs.edit { putString(KEY_UMUR, newUmur) }
        }
    }

    fun reset() {
        _nama.value = ""
        _umur.value = ""
        prefs.edit {
            remove(KEY_NAMA)
            remove(KEY_UMUR)
        }
    }

    companion object {
        private const val PREFS_NAME = "data_diri_prefs"
        private const val KEY_NAMA = "key_nama"
        private const val KEY_UMUR = "key_umur"
    }
}
