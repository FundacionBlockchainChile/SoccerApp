package com.example.soccerapp.ui.navigation

import TeamDetailScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.soccerapp.ui.view.TeamListScreen
import com.example.soccerapp.viewmodel.TeamViewModel

@Composable
fun NavGraph(startDestination: String = "teamList") {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("teamList") {
            val viewModel: TeamViewModel = hiltViewModel()
            TeamListScreen(viewModel = viewModel, navController = navController)
        }
        composable("teamDetail/{teamId}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId")?.toInt() ?: 0
            val viewModel: TeamViewModel = hiltViewModel()
            TeamDetailScreen(teamId = teamId, viewModel = viewModel, navController = navController)
        }
    }
}
