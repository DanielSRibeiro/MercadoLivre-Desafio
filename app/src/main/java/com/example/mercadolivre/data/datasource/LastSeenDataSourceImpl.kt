package com.example.mercadolivre.data.datasource

import com.example.core.data.local.LastSeenDataSource
import com.example.core.domain.model.PicturesResponse
import com.example.core.domain.model.Product
import com.example.core.domain.model.ProductResults
import com.example.mercadolivre.data.local.dao.MlDao
import com.example.mercadolivre.data.local.entity.mapper.toLastSeenEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LastSeenDataSourceImpl @Inject constructor(
    private val mlDao: MlDao
) : LastSeenDataSource {

    override suspend fun getLastSeen(): List<Product> {
        return mlDao.getLastSeen().map {
            Product(
                id = it.id,
                data = ProductResults(
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    basePrice = it.basePrice,
                    thumbnail = it.thumbnail,
                    pictures = it.pictures.map { picture ->
                        PicturesResponse(
                            id = picture.id,
                            url = picture.url
                        )
                    }
                )
            )
        }
    }

    override suspend fun addedInLastSeen(product: Product) {
        mlDao.addedInLastSeen(product.toLastSeenEntity())
    }
}