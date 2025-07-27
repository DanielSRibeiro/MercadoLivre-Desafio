package com.example.mercadolivre.data.local.entity.mapper

import com.example.core.domain.model.PicturesResponse
import com.example.core.domain.model.Product
import com.example.core.domain.model.ProductResults
import com.example.mercadolivre.data.local.entity.LastSeenEntity
import com.example.mercadolivre.data.local.entity.PicturesEntity
import com.example.mercadolivre.data.local.entity.ProductEntity

fun Product.toLastSeenEntity() = LastSeenEntity(
    id = this.id,
    title = this.data.title,
    description = this.data.description,
    price = this.data.price,
    basePrice = this.data.basePrice,
    thumbnail = this.data.thumbnail,
    pictures = this.data.pictures.map {
        PicturesEntity(
            id = it.id,
            url = it.url
        )
    }
)

fun Product.toProductEntity() = ProductEntity(
    id = this.id,
    title = this.data.title,
    description = this.data.description,
    price = this.data.price,
    basePrice = this.data.basePrice,
    thumbnail = this.data.thumbnail,
    pictures = this.data.pictures.map {
        PicturesEntity(
            id = it.id,
            url = it.url
        )
    }
)

fun ProductEntity.toProductEntity() = Product(
    id = this.id,
    data = ProductResults(
        title = this.title,
        description = this.description,
        price = this.price,
        basePrice = this.basePrice,
        thumbnail = this.thumbnail,
        pictures = this.pictures.map {
            PicturesResponse(
                id = it.id,
                url = it.url
            )
        }
    ),
)