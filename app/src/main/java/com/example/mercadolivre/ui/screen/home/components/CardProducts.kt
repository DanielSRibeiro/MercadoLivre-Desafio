package com.example.mercadolivre.ui.screen.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.model.Product
import com.example.mercadolivre.ui.components.commo.AsyncImageUrl

@Composable
fun CardProducts(
    modifier: Modifier = Modifier,
    title: String = "Vistos recentemente",
    product: List<Product>,
    onNavigateToDetail: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Text(
                text = title,
                modifier = modifier
                    .padding(bottom = 8.dp, top = 16.dp, start = 16.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(product.size) { index ->
                    Column(
                        Modifier
                            .width(200.dp)
                            .clickable {
                                onNavigateToDetail()
                            }
                    ) {
                        AsyncImageUrl(
                            modifier = Modifier
                                .height(200.dp),
                            contentScale = ContentScale.Crop,
                            imageUrl = product[index].data.pictures[0].url,
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            text = product[index].data.title,
                            maxLines = 2
                        )

                        if (product[index].data.price != product[index].data.basePrice) {
                            Text(
                                modifier = Modifier
                                    .padding(bottom = 8.dp),
                                text = "R$${product[index].data.basePrice}",
                                maxLines = 1,
                                fontSize = 14.sp,
                                style = TextStyle(
                                    textDecoration = TextDecoration.LineThrough
                                )
                            )
                        }

                        Text(
                            modifier = Modifier
                                .padding(bottom = 24.dp),
                            text = "R$${product[index].data.price}",
                            maxLines = 1,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}