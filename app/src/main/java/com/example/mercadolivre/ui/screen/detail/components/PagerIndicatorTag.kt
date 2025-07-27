package com.example.mercadolivre.ui.screen.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mercadolivre.ui.theme.black
import com.example.mercadolivre.ui.theme.white

@Composable
fun PagerIndicatorTag(
    modifier: Modifier,
    text: String
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .background(white, RoundedCornerShape(25.dp))
            .padding(8.dp, 4.dp)
    ) {
        Text(
            text = text,
            color = black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}