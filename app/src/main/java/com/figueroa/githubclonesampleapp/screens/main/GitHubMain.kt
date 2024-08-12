package com.figueroa.githubclonesampleapp.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.figueroa.githubclonesampleapp.R
import com.figueroa.githubclonesampleapp.components.GitHubCloneAppBar
import com.figueroa.githubclonesampleapp.components.TitleSection
import com.figueroa.githubclonesampleapp.components.WorkSection
import com.figueroa.githubclonesampleapp.data.WorkItem
import com.figueroa.githubclonesampleapp.navigation.AppScreens
import com.figueroa.githubclonesampleapp.ui.theme.Black50
import com.figueroa.githubclonesampleapp.ui.theme.Blue50
import com.figueroa.githubclonesampleapp.ui.theme.Gray30
import com.figueroa.githubclonesampleapp.ui.theme.Green80
import com.figueroa.githubclonesampleapp.ui.theme.Orange70
import com.figueroa.githubclonesampleapp.ui.theme.Purple100
import com.figueroa.githubclonesampleapp.ui.theme.Yellow90

@Composable
fun GitHubMainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            GitHubCloneAppBar(title = "Home", isHome = true, navController = navController)
        }
    ) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            TitleSection(label = "My Work")

            val workItems = getWorkItems(navController)

            LazyColumn {
                items(workItems) { item ->
                    WorkSection(
                        backgroundColor = item.color,
                        icon = item.icon,
                        foregroundColor = Color.White,
                        label = item.label,
                        hasInfo = false,
                        onClick = item.onClick
                    )
                }
            }
        }
    }
}

@Composable
fun getWorkItems(navController: NavController): List<WorkItem> {
    return listOf(
        WorkItem(Green80, R.drawable.ic_issues, "Issues") {
            // TODO: Add behavior for Issues
        },
        WorkItem(Blue50, R.drawable.ic_pull_request, "Pull Request") {
            // TODO: Add behavior for Pull Request
        },
        WorkItem(Purple100, R.drawable.ic_discussions, "Discussions") {
            // TODO: Add behavior for Discussions
        },
        WorkItem(Gray30, R.drawable.ic_projects, "Projects") {
            // TODO: Add behavior for Projects
        },
        WorkItem(Black50, R.drawable.ic_repositories, "Repositories") {
            Log.i("Enter Button", "Repositories")
            navController.navigate(AppScreens.GitHubRepositoryListScreen.name)
        },
        WorkItem(Orange70, R.drawable.ic_organizations, "Organizations") {
            // TODO: Add behavior for Organizations
        },
        WorkItem(Yellow90, R.drawable.ic_starred, "Starred") {
            // TODO: Add behavior for Starred
        }
    )
}