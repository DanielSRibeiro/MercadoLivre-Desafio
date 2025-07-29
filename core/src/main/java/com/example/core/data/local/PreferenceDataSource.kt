package com.example.core.data.local

interface PreferenceDataSource {
    fun getStringList(): List<String>
    fun saveStringList(list: List<String>)
}