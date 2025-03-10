package com.figueroa.githubclonesampleapp.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.figueroa.githubclonesampleapp.R
import com.figueroa.githubclonesampleapp.components.GitHubCloneAppBar
import com.figueroa.githubclonesampleapp.components.RepositoryItem
import com.figueroa.githubclonesampleapp.data.Resource
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryInformation
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun GitHubRepositoryList(
    navController: NavController,
    gitHubRepositoryListViewModel: GitHubRepositoryListViewModel,
) {
    var listState by remember { mutableStateOf<Resource<List<GitHubRepositoryInformation>>>(Resource.Loading()) }
    var currentPage by remember { mutableStateOf(1) }
    var isLastPage by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        listState = gitHubRepositoryListViewModel.getGitHubRepositories(currentPage)
    }

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
            SwipeRefresh(
                state = SwipeRefreshState(isRefreshing = listState is Resource.Loading),
                onRefresh = {
                    scope.launch {
                        currentPage = 1
                        isLastPage = false
                        listState = Resource.Loading()
                        listState = gitHubRepositoryListViewModel.getGitHubRepositories(currentPage)
                    }
                }
            ) {
                when (listState) {
                    is Resource.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is Resource.Success -> {
                        val listOfGitHubRepositories = listState.data ?: emptyList()
                        LazyColumn {
                            items(items = listOfGitHubRepositories) { repository ->
                                RepositoryItem(
                                    gitHubRepositoryInformation = repository,
                                    navController = navController
                                )
                            }
                            item {
                                if (listState is Resource.Loading) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                } else {
                                    LaunchedEffect(Unit) {
                                        scope.launch {
                                            if (!isLastPage) {
                                                currentPage++
                                                val newState =
                                                    gitHubRepositoryListViewModel.getGitHubRepositories(
                                                        currentPage
                                                    )
                                                if (newState is Resource.Success && newState.data!!.isNotEmpty()) {
                                                    listState =
                                                        Resource.Success((listState as Resource.Success).data!! + newState.data)
                                                } else {
                                                    isLastPage = true
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    is Resource.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Error: ${listState.message}")
                            // Aquí puedes agregar una imagen en lugar de o además del texto
                            Image(
                                painter = painterResource(id = R.drawable.octocat_logo),
                                contentDescription = "logo"
                            )
                        }
                    }
                }
            }
        }
    }
}