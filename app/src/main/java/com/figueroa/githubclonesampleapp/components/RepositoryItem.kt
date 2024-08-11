package com.figueroa.githubclonesampleapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryInformation
import com.figueroa.githubclonesampleapp.R
import com.figueroa.githubclonesampleapp.navigation.AppScreens
import com.figueroa.githubclonesampleapp.ui.theme.Gray30
import com.figueroa.githubclonesampleapp.ui.theme.Purple40

@Composable
fun RepositoryItem(
    gitHubRepositoryInformation: GitHubRepositoryInformation,
    navController: NavController
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate(AppScreens.GitHubRepositoryDetailScreen.name + "/${gitHubRepositoryInformation.owner.login}/${gitHubRepositoryInformation.name}")
        }) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(gitHubRepositoryInformation.owner.avatar_url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(16.dp)
                )
                Text(text = gitHubRepositoryInformation.owner.login, color = Gray30)
            }
            Text(text = gitHubRepositoryInformation.name, fontWeight = FontWeight.Bold)

        }
        HorizontalDivider()
    }
}

@Composable
@Preview
fun RepositoryItemPreview() {
    val navController = NavController(LocalContext.current)
    RepositoryItem(
        gitHubRepositoryInformation = GitHubRepositoryInformation.createDummy(),
        navController = navController
    )
}