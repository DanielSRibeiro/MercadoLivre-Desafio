package com.example.mercadolivre.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val description: String,
    val price: Double,
    val basePrice: Double,
    val thumbnail: String,
    val pictures: List<PicturesEntity>
)

data class PicturesEntity(
    val id: String,
    val url: String,
)