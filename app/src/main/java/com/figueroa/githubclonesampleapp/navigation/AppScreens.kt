package com.figueroa.githubclonesampleapp.navigation

enum class AppScreens {
    GitHubMainScreen,
    GitHubRepositoryListScreen,
    GitHubRepositoryDetailScreen,
    ;

    companion object {
        fun fromRoute(route: String?): AppScreens = when (route?.substringBefore("/")) {
            GitHubMainScreen.name -> GitHubMainScreen
            GitHubRepositoryListScreen.name -> GitHubRepositoryListScreen
            GitHubRepositoryDetailScreen.name -> GitHubRepositoryDetailScreen
            null -> GitHubMainScreen
            else -> throw IllegalArgumentException("Route $route is not recognized.")
        }
    }
}