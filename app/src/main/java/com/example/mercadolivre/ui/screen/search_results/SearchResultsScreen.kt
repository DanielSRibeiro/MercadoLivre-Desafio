package com.example.mercadolivre.ui.screen.search_results

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SearchResultsScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        //TODO() : Colocar o que foi pesquisado
        Text(
            "Oque foi pesquisado",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            "Quantidade de Resultados",
            style = MaterialTheme.typography.bodySmall
        )
        SearchContent()

    }
}

@Composable
fun SearchContent(modifier: Modifier = Modifier) {

}