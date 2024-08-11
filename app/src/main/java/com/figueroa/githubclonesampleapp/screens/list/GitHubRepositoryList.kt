package com.figueroa.githubclonesampleapp.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.figueroa.githubclonesampleapp.R
import com.figueroa.githubclonesampleapp.components.GitHubCloneAppBar
import com.figueroa.githubclonesampleapp.components.RepositoryItem
import com.figueroa.githubclonesampleapp.model.GitHubRepository

@Composable
fun GitHubRepositoryList(
    navController: NavController,
    gitHubRepositoryListViewModel: GitHubRepositoryListViewModel,
    query: String,
    perPage: Int,
    page: Int
) {
    Scaffold(
        topBar = {
            GitHubCloneAppBar(
                title = "Repositories",
                isHome = false,
                icon = R.drawable.ic_arrow_back,
                navController = navController,
                onBackPressed = { navController.popBackStack() })
        }
    ) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            val listOfGitHubRepositories = gitHubRepositoryListViewModel.list
            if (gitHubRepositoryListViewModel.isLoading) {
                Row(
                    modifier = Modifier.padding(end = 2.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            } else {
                LazyColumn {
                    items(items = listOfGitHubRepositories) { repository ->
                        RepositoryItem(
                            gitHubRepositoryInformation = repository,
                            navController = navController
                        )
                    }
                }
            }
        }

    }
}