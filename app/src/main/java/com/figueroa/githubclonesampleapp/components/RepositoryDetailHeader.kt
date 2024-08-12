package com.figueroa.githubclonesampleapp.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.figueroa.githubclonesampleapp.R
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryInformation
import com.figueroa.githubclonesampleapp.ui.theme.Gray30
import com.figueroa.githubclonesampleapp.util.formatNumber
import com.figueroa.githubclonesampleapp.util.openUrl
import com.figueroa.githubclonesampleapp.util.removeHttpPrefix

@Composable
fun RepositoryDetailHeader(
    gitHubRepositoryInformation: GitHubRepositoryInformation,
    navController: NavController
) {
    val context = LocalContext.current
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
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
                    contentDescription = stringResource(id = R.string.avatar_description),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(16.dp)
                )
                Text(text = gitHubRepositoryInformation.owner.login, color = Gray30)
            }
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                text = gitHubRepositoryInformation.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                text = gitHubRepositoryInformation.description
            )
            Row(
                modifier = Modifier.clickable {
                    openUrl(
                        context,
                        gitHubRepositoryInformation.homepage
                    )
                },
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_link),
                    contentDescription = stringResource(id = R.string.homepage_description),
                    modifier = Modifier.size(16.dp),
                    tint = Gray30
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                    text = removeHttpPrefix(gitHubRepositoryInformation.homepage),
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_starred),
                    contentDescription = stringResource(id = R.string.watchers_description),
                    modifier = Modifier.size(16.dp),
                    tint = Gray30
                )
                Text(
                    text = stringResource(
                        id = R.string.label_stars,
                        formatNumber(gitHubRepositoryInformation.stargazers_count)
                    ),
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_forks),
                        contentDescription = stringResource(id = R.string.forks_description),
                        modifier = Modifier.size(16.dp),
                        tint = Gray30
                    )
                    Text(
                        text = stringResource(
                            id = R.string.label_forks,
                            formatNumber(gitHubRepositoryInformation.forks_count)
                        ),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(3F),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.White,
                        disabledContentColor = Color.LightGray
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_starred),
                            contentDescription = stringResource(id = R.string.star_description),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = stringResource(id = R.string.button_star))
                    }
                }

                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.White,
                        disabledContentColor = Color.LightGray
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.ic_notifications),
                        contentDescription = stringResource(id = R.string.notifications_description)
                    )
                }
            }
        }

        HorizontalDivider()
    }


}

@Composable
@Preview
fun RepositoryDetailHeaderPreview() {
    val navController = NavController(LocalContext.current)
    RepositoryDetailHeader(
        gitHubRepositoryInformation = GitHubRepositoryInformation.createDummy(),
        navController = navController
    )
}