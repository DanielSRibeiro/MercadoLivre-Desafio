package com.example.mercadolivre.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val pictures: List<PicturesEntity>,
    val attribute: List<AttributeEntity>,
    val keywords: String,
    val total: Int,
    val createdAt: Long = System.currentTimeMillis()
)

data class PicturesEntity(
    val id: String,
    val url: String,
)

data class AttributeEntity(
    val name: String,
    val valueName: String
)