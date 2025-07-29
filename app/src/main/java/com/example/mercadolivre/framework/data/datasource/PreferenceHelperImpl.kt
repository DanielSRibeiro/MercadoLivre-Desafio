package com.example.mercadolivre.framework.data.datasource

import android.content.Context
import android.content.SharedPreferences
import com.example.core.data.local.PreferenceDataSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : PreferenceDataSource {

    private val NAME = "research_history_name"
    private val KEY = "research_history_key"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    override fun getStringList(): List<String> {
        val prefs = getPrefs(context)
        val json = prefs.getString(KEY, null)

        return if (json != null) {
            val type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }

    override fun saveStringList(list: List<String>) {
        val prefs = getPrefs(context)
        val editor = prefs.edit()
        val json = Gson().toJson(list)
        editor.putString(KEY, json)
        editor.apply()
    }
}