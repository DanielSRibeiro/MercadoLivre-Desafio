package com.example.mercadolivre.ui.screen.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.model.Attribute
import com.example.core.domain.model.Picture
import com.example.core.domain.model.ProductResults
import com.example.mercadolivre.R
import com.example.mercadolivre.ui.components.commo.AsyncImageUrl
import com.example.mercadolivre.ui.screen.detail.model.ProductResultsDetail
import com.example.mercadolivre.ui.screen.detail.model.toProductResultsDetail

@Composable
fun CardProducts(
    productData: List<ProductResults>,
    onNavigateToDetail: (ProductResultsDetail) -> Unit,
    isFavorite: Boolean = false
) {
    Column {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(productData) { product ->
                Card(
                    modifier = Modifier
                        .width(320.dp)
                        .height(180.dp)
                        .clickable {
                            onNavigateToDetail(product.toProductResultsDetail())
                        },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    ),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AsyncImageUrl(
                            modifier = Modifier
                                .height(120.dp)
                                .aspectRatio(3f / 4f)
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Inside,
                            imageUrl = product.pictures.firstOrNull()?.url.orEmpty()
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.Top),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = product.name,
                                style = MaterialTheme.typography.bodyLarge,
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            LazyColumn {
                                items(product.attribute.take(2).size) { index ->
                                    val attribute = product.attribute[index]
                                    Text(
                                        text = attribute.name + ": " + attribute.valueName,
                                        fontSize = 12.sp
                                    )
                                }
                            }

                        }
                        if (isFavorite) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = stringResource(R.string.label_favorite),
                                tint = Color.Red,
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.Bottom)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardProductsPreview() {
    val mockProducts = listOf(
        ProductResults(
            id = "123",
            name = "Touch QN3223 Android",
            pictures = listOf(Picture(id = "1", url = "https://placekitten.com/200/200")),
            attribute = listOf(
                Attribute(name = "Tela", valueName = "Smart TV")
            ),
            keywords = "",
            total = 0
        ),
        ProductResults(
            id = "456",
            name = "Celular Siemens M50",
            pictures = listOf(Picture(id = "1", url = "https://placekitten.com/210/210")),
            attribute = listOf(
                Attribute(name = "Modelo", valueName = "M50"),
                Attribute(name = "Cor", valueName = "Laranja")
            ),
            keywords = "",
            total = 0
        )
    )

    MaterialTheme {
        CardProducts(
            productData = mockProducts,
            onNavigateToDetail = {}
        )
    }
}
