package com.example.mercadolivre.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mercadolivre.data.local.dao.MlDao
import com.example.mercadolivre.data.local.entity.LastSeenEntity
import com.example.mercadolivre.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, LastSeenEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MlDatabase : RoomDatabase() {
    abstract fun mlDao(): MlDao
}