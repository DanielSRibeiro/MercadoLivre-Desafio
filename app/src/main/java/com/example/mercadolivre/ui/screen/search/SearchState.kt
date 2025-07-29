package com.example.mercadolivre.ui.screen.search

data class SearchState(
    val query: String = "",
    val researchHistory: List<String> = emptyList(),
)