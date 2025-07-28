package com.example.mercadolivre.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PreferenceHelper {
    private const val NAME = "research_history_name"
    private const val KEY = "research_history_key"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    fun saveStringList(context: Context, list: List<String>) {
        val prefs = getPrefs(context)
        val editor = prefs.edit()
        val json = Gson().toJson(list)
        editor.putString(KEY, json)
        editor.apply()
    }

    fun getStringList(context: Context): List<String> {
        val prefs = getPrefs(context)
        val json = prefs.getString(KEY, null)

        return if (json != null) {
            val type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }
}