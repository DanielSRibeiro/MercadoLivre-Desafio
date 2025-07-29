package com.example.mercadolivre.framework.data.local

import androidx.room.TypeConverter
import com.example.mercadolivre.framework.data.local.entity.AttributeEntity
import com.example.mercadolivre.framework.data.local.entity.PicturesEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromPictures(pictures: List<PicturesEntity>): String {
        return gson.toJson(pictures)
    }

    @TypeConverter
    fun toPictures(json: String): List<PicturesEntity> {
        val type = object : TypeToken<List<PicturesEntity>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromAttribute(attribute: List<AttributeEntity>): String {
        return gson.toJson(attribute)
    }

    @TypeConverter
    fun toAttribute(json: String): List<AttributeEntity> {
        val type = object : TypeToken<List<AttributeEntity>>() {}.type
        return gson.fromJson(json, type)
    }
}