package com.example.mercadolivre.framework.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mercadolivre.framework.data.local.entity.LastSeenEntity
import com.example.mercadolivre.framework.data.local.entity.ProductEntity

@Dao
interface MlDao {
    //LastSeen
    @Query("SELECT * FROM last_seen ORDER BY createdAt DESC")
    fun getLastSeen(): List<LastSeenEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addedInLastSeen(id: LastSeenEntity)

    @Query("DELETE FROM last_seen WHERE createdAt = (SELECT createdAt FROM last_seen ORDER BY createdAt ASC LIMIT 1)")
    suspend fun deleteLastItem()

    //Favorite
    @Query("SELECT * FROM product ORDER BY createdAt DESC")
    fun getAllFavorites(): List<ProductEntity>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun isFavorite(id: String): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addedInProduct(id: ProductEntity)

    @Delete
    suspend fun deleteProduct(id: ProductEntity)
}