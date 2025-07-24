package com.example.mercadolivre.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.core.data.local.AccessTokenDataSource
import com.example.core.domain.model.AccessToken
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AccessTokenDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AccessTokenDataSource {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val gson = Gson()

    override suspend fun saveToken(token: AccessToken) {
        val json = gson.toJson(token)
        prefs.edit().putString("access_token", json).apply()
    }

    override suspend fun getToken(): AccessToken? {
        val json = prefs.getString("access_token", null) ?: return null
        return gson.fromJson(json, AccessToken::class.java)
    }
}
