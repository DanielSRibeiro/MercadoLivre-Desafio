package com.example.testing.model

import com.example.core.domain.model.Attribute
import com.example.core.domain.model.Picture
import com.example.core.domain.model.ProductResults

class ProductResultsFactory {

    fun create(productFactory: ProductFactory) = when (productFactory) {
        ProductFactory.FirstIphone -> ProductResults(
            id = "MLB6055020",
            name = "Iphone 6 iPhone 6 128 GB Dourado",
            pictures = listOf(
                Picture(
                    id = "704474-MLA43772985892_102020",
                    url = "https://mla-s2-p.mlstatic.com/704474-MLA43772985892_102020-F.jpg"
                ),
                Picture(
                    id = "832687-MLA43773016789_102020",
                    url = "https://mla-s1-p.mlstatic.com/832687-MLA43773016789_102020-F.jpg"
                )
            ),
            attribute = listOf(
                Attribute(
                    name = "Marca",
                    valueName = "Apple"
                ),
                Attribute(
                    name = "Linha",
                    valueName = "Iphone 6"
                )
            ),
            keywords = "iphone",
            total = 20
        )

        ProductFactory.SecondIphone -> ProductResults(
            id = "MLB15542659",
            name = "iPhone 8",
            pictures = listOf(
                Picture(
                    id = "729014-MLA43694099452_102020",
                    url = "https://mla-s2-p.mlstatic.com/729014-MLA43694099452_102020-F.jpg"
                ),
                Picture(
                    id = "639795-MLA43694219079_102020",
                    url = "https://mla-s2-p.mlstatic.com/639795-MLA43694219079_102020-F.jpg"
                )
            ),
            attribute = listOf(
                Attribute(
                    name = "Marca",
                    valueName = "Apple"
                ),
                Attribute(
                    name = "Linha",
                    valueName = "iPhone 8"
                )
            ),
            keywords = "iphone",
            total = 20
        )
    }

    sealed class ProductFactory {
        data object FirstIphone : ProductFactory()
        data object SecondIphone : ProductFactory()
    }
}