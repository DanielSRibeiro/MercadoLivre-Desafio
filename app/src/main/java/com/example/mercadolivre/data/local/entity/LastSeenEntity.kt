package com.example.mercadolivre.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_seen")
data class LastSeenEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val description: String,
    val price: Double,
    val basePrice: Double,
    val thumbnail: String,
    val pictures: List<PicturesEntity>
)