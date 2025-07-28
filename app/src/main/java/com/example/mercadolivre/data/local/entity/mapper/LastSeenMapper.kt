package com.example.mercadolivre.data.local.entity.mapper

import com.example.core.domain.model.Attribute
import com.example.core.domain.model.Picture
import com.example.core.domain.model.ProductResults
import com.example.mercadolivre.data.local.entity.AttributeEntity
import com.example.mercadolivre.data.local.entity.LastSeenEntity
import com.example.mercadolivre.data.local.entity.PicturesEntity
import com.example.mercadolivre.data.local.entity.ProductEntity

fun ProductResults.toLastSeenEntity() = LastSeenEntity(
    id = this.id,
    name = this.name,
    pictures = this.pictures.map {
        PicturesEntity(
            id = it.id,
            url = it.url
        )
    },
    attribute = this.attribute.map {
        AttributeEntity(
            name = it.name,
            valueName = it.valueName
        )
    },
    keywords = this.keywords,
    total = this.total
)

fun ProductResults.toProductEntity() = ProductEntity(
    id = this.id,
    name = this.name,
    pictures = this.pictures.map {
        PicturesEntity(
            id = it.id,
            url = it.url
        )
    },
    attribute = this.attribute.map {
        AttributeEntity(
            name = it.name,
            valueName = it.valueName
        )
    },
    keywords = this.keywords,
    total = this.total
)

fun ProductEntity.toProductEntity() = ProductResults(
    id = this.id,
    name = this.name,
    pictures = this.pictures.map { picture ->
        Picture(
            id = picture.id,
            url = picture.url
        )
    },
    keywords = this.keywords,
    total = this.total,
    attribute = this.attribute.map { attribute ->
        Attribute(
            name = attribute.name,
            valueName = attribute.valueName
        )
    },
)

fun LastSeenEntity.toProductResult() = ProductResults(
    id = this.id,
    name = this.name,
    pictures = this.pictures.map { picture ->
        Picture(
            id = picture.id,
            url = picture.url
        )
    },
    keywords = this.keywords,
    total = this.total,
    attribute = this.attribute.map { attribute ->
        Attribute(
            name = attribute.name,
            valueName = attribute.valueName
        )
    },
)