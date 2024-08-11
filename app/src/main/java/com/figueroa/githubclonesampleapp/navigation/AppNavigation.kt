package com.figueroa.githubclonesampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.figueroa.githubclonesampleapp.screens.main.GitHubMainScreen
import com.figueroa.githubclonesampleapp.screens.details.GitHubRepositoryDetails
import com.figueroa.githubclonesampleapp.screens.list.GitHubRepositoryList
import com.figueroa.githubclonesampleapp.screens.list.GitHubRepositoryListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.GitHubMainScreen.name) {
        composable(AppScreens.GitHubMainScreen.name) {
            GitHubMainScreen(navController)
        }
        composable(AppScreens.GitHubRepositoryListScreen.name) {
            val gitHubRepositoryListViewModel = getViewModel<GitHubRepositoryListViewModel>()
            GitHubRepositoryList(
                navController,
                gitHubRepositoryListViewModel,
                query = "Kotlin",
                perPage = 10,
                page = 1
            )
        }
        composable(
            AppScreens.GitHubRepositoryDetailScreen.name + "/{owner}/{name}",
            arguments = listOf(navArgument("owner") {
                type = NavType.StringType
            }, navArgument("name") {
                type = NavType.StringType
            })
        ) {
            backStackEntry ->
            backStackEntry.arguments?.getString("owner").let { owner ->
                backStackEntry.arguments?.getString("name").let { name ->
                    GitHubRepositoryDetails(navController, owner!!, name!!, getViewModel())
                }
        }
    }
}
}