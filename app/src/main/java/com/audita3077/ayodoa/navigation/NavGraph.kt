package com.audita3077.ayodoa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.audita3077.ayodoa.ui.screen.DetailScreen
import com.audita3077.ayodoa.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen(navController)
        }
        composable(Screen.Detail.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                DetailScreen(navController, id)
            }
        }
//        composable(Screen.Form.route) {
//            FormScreen(navController)
//        }
    }
}
