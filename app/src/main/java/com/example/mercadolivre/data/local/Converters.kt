package com.example.mercadolivre.data.local

import androidx.room.TypeConverter
import com.example.mercadolivre.data.local.entity.PicturesEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromPictures(pictures: List<PicturesEntity>): String {
        return Gson().toJson(pictures)
    }

    @TypeConverter
    fun toPictures(json: String): List<PicturesEntity> {
        val type = object : TypeToken<List<PicturesEntity>>() {}.type
        return Gson().fromJson(json, type)
    }
}