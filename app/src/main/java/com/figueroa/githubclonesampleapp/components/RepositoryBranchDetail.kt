package com.figueroa.githubclonesampleapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.figueroa.githubclonesampleapp.R
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryInformation

@Composable
fun RepositoryBranchDetail(
    gitHubRepositoryInformation: GitHubRepositoryInformation,
    navController: NavController
) {
    Column {
        Column {
            TitleSection(
                icon = R.drawable.ic_branch,
                label = gitHubRepositoryInformation.default_branch,
                buttonText = stringResource(id = R.string.button_change_branch),
                isHome = false
            )
            WorkSection(
                backgroundColor = Color.LightGray,
                icon = R.drawable.ic_code,
                foregroundColor = Color.Black,
                label = stringResource(id = R.string.label_code),
                hasInfo = false,
                onClick = {})
            WorkSection(
                backgroundColor = Color.LightGray,
                icon = R.drawable.ic_commits,
                foregroundColor = Color.Black,
                label = stringResource(id = R.string.label_commits),
                hasInfo = false,
                onClick = {})
        }
        HorizontalDivider()
    }
}

@Composable
@Preview
fun RepositoryBranchDetailPreview() {
    val navController = NavController(LocalContext.current)
    RepositoryBranchDetail(
        gitHubRepositoryInformation = GitHubRepositoryInformation.createDummy(),
        navController = navController
    )
}