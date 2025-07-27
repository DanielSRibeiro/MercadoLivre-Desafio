package com.example.mercadolivre.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mercadolivre.data.local.entity.LastSeenEntity
import com.example.mercadolivre.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MlDao {
    //LastSeen
    @Query("SELECT * FROM last_seen")
    fun getLastSeen(): List<LastSeenEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addedInLastSeen(id: LastSeenEntity)

    //Favorite
    @Query("SELECT * FROM product")
    fun getAllFavorites(): List<ProductEntity>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun isFavorite(id: String): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addedInProduct(id: ProductEntity)

    @Delete
    suspend fun deleteProduct(id: ProductEntity)
}