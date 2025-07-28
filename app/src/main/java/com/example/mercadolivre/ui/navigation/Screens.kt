package com.example.mercadolivre.ui.navigation

import com.example.mercadolivre.util.Constants.SEARCH_RESULT_ARGUMENT_KEY

sealed class Screens(val route: String) {
    data object Home : Screens("home_screen")
    data object Search : Screens("search_screen")
    data object SearchResults : Screens(
        route = "search_results_screen?$SEARCH_RESULT_ARGUMENT_KEY={$SEARCH_RESULT_ARGUMENT_KEY}"
    ) {
        fun passQuery(query: String): String =
            "search_results_screen?$SEARCH_RESULT_ARGUMENT_KEY=$query"
    }

    data object Detail : Screens(
        "detail_screen"
    )
}