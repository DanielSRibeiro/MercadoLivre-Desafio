package com.example.mercadolivre.framework.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_seen")
data class LastSeenEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val pictures: List<PicturesEntity>,
    val attribute: List<AttributeEntity>,
    val keywords: String,
    val total: Int,
    val createdAt: Long = System.currentTimeMillis()
)