package com.example.mercadolivre.ui.screen.detail

import android.os.Parcelable
import com.example.core.domain.model.Attribute
import com.example.core.domain.model.Picture
import com.example.core.domain.model.ProductResults
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResultsDetail(
    val id: String,
    val name: String,
    val pictures: List<PictureDetail>,
    val attribute: List<AttributeDetail>,
    val keywords: String,
    val total: Int,
) : Parcelable

@Parcelize
data class PictureDetail(
    val id: String,
    val url: String,
) : Parcelable

@Parcelize
data class AttributeDetail(
    val name: String,
    val valueName: String
) : Parcelable


fun ProductResults.toProductResultsDetail() = ProductResultsDetail(
    id = this.id,
    name = this.name,
    keywords = this.keywords,
    total = this.total,
    pictures = this.pictures.map { picture ->
        PictureDetail(
            id = picture.id,
            url = picture.url
        )
    },
    attribute = this.attribute.map { attribute ->
        AttributeDetail(
            name = attribute.name,
            valueName = attribute.valueName
        )
    }
)

fun ProductResultsDetail.toProductResults() = ProductResults(
    id = this.id,
    name = this.name,
    keywords = this.keywords,
    total = this.total,
    pictures = this.pictures.map { picture ->
        Picture(
            id = picture.id,
            url = picture.url
        )
    },
    attribute = this.attribute.map { attribute ->
        Attribute(
            name = attribute.name,
            valueName = attribute.valueName
        )
    }
)