package com.figueroa.githubclonesampleapp.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.figueroa.githubclonesampleapp.R
import com.figueroa.githubclonesampleapp.components.GitHubCloneAppBar
import com.figueroa.githubclonesampleapp.components.RepositoryBranchDetail
import com.figueroa.githubclonesampleapp.components.RepositoryDetailHeader
import com.figueroa.githubclonesampleapp.components.WorkSection
import com.figueroa.githubclonesampleapp.data.Resource
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryInformation
import com.figueroa.githubclonesampleapp.ui.theme.Blue50
import com.figueroa.githubclonesampleapp.ui.theme.Green80
import com.figueroa.githubclonesampleapp.ui.theme.Orange90
import com.figueroa.githubclonesampleapp.ui.theme.Red70
import com.figueroa.githubclonesampleapp.ui.theme.Yellow90
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun GitHubRepositoryDetails(
    navController: NavController,
    owner: String,
    name: String,
    gitHubRepositoryDetailsViewModel: GitHubRepositoryDetailsViewModel
) {
    var showMoreSections by remember { mutableStateOf(false) }
    var isRefreshing by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            GitHubCloneAppBar(
                title = "",
                isHome = false,
                icon = R.drawable.ic_arrow_back,
                navController = navController,
                onBackPressed = { navController.popBackStack() })
        }
    ) { contentPadding ->
        var repoInformation =
            produceState<Resource<GitHubRepositoryInformation>>(initialValue = Resource.Loading()) {
                value = gitHubRepositoryDetailsViewModel.getGitHubRepositoryInformation(owner, name)
            }.value

        LaunchedEffect(isRefreshing) {
            if (isRefreshing) {
                repoInformation =
                    gitHubRepositoryDetailsViewModel.getGitHubRepositoryInformation(owner, name)
                isRefreshing = false
            }
        }

        SwipeRefresh(
            state = SwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = {
                isRefreshing = true
            }
        ) {
            if (repoInformation.data == null) {
                Column(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Text(text = "Loading...")
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(contentPadding)
                        .verticalScroll(state = rememberScrollState())
                ) {
                    RepositoryDetailHeader(
                        gitHubRepositoryInformation = repoInformation.data!!,
                        navController = navController
                    )
                    WorkSection(
                        backgroundColor = Green80,
                        icon = R.drawable.ic_issues,
                        foregroundColor = Color.White,
                        label = "Issues",
                        hasInfo = true,
                        info = repoInformation.data!!.open_issues_count.toString()
                    ) {}
                    WorkSection(
                        backgroundColor = Blue50,
                        icon = R.drawable.ic_pull_request,
                        foregroundColor = Color.White,
                        label = "Pull Request",
                        hasInfo = false
                    ) {}
                    WorkSection(
                        backgroundColor = Yellow90,
                        icon = R.drawable.ic_actions,
                        foregroundColor = Color.White,
                        label = "Actions",
                        hasInfo = false
                    ) {}
                    if (!showMoreSections) {
                        WorkSection(
                            backgroundColor = Color.LightGray,
                            icon = R.drawable.ic_more_horiz,
                            foregroundColor = Color.Black,
                            label = "More",
                            hasInfo = true
                        ) {
                            showMoreSections = true
                        }
                    } else {
                        WorkSection(
                            backgroundColor = Orange90,
                            icon = R.drawable.ic_users,
                            foregroundColor = Color.White,
                            label = "Contributors",
                            hasInfo = false
                        ) {}
                        WorkSection(
                            backgroundColor = Yellow90,
                            icon = R.drawable.ic_watchers,
                            foregroundColor = Color.White,
                            label = "Watchers",
                            hasInfo = false
                        ) {}
                        WorkSection(
                            backgroundColor = Red70,
                            icon = R.drawable.ic_license,
                            foregroundColor = Color.White,
                            label = "License",
                            hasInfo = true,
                            info = if (repoInformation.data!!.license == null) {
                                ""
                            } else {
                                repoInformation.data!!.license?.spdx_id.toString()
                            }
                        ) {}
                    }
                    HorizontalDivider()
                    RepositoryBranchDetail(
                        gitHubRepositoryInformation = repoInformation.data!!,
                        navController = navController
                    )

                }
            }
        }
    }
}