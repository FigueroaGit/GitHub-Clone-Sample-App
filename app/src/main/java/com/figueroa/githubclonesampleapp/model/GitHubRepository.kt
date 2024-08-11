package com.figueroa.githubclonesampleapp.model

data class GitHubRepository(
    val incomplete_results: Boolean,
    val items: List<GitHubRepositoryInformation>,
    val total_count: Int
)