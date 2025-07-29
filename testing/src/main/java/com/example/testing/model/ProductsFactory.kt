package com.example.testing.model

import com.example.core.domain.model.Paging
import com.example.core.domain.model.Products

class ProductsFactory {

    fun create() = Products(
        results = listOf(
            ProductResultsFactory().create(ProductResultsFactory.ProductFactory.FirstIphone),
            ProductResultsFactory().create(ProductResultsFactory.ProductFactory.SecondIphone),
        ),
        keywords = "iphone",
        paging = Paging(
            total = 20,
            limit = 20,
            offset = 0
        )
    )
}