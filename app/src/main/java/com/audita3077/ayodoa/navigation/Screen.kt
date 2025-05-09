package com.audita3077.ayodoa.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object Detail : Screen("detail_screen/{id}") {
        fun withId(id: Int) = "detail_screen/$id"
    }
    object Form : Screen("form_screen")
}

