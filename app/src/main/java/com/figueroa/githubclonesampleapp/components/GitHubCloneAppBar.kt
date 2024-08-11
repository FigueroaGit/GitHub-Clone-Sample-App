package com.figueroa.githubclonesampleapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.figueroa.githubclonesampleapp.R
import com.figueroa.githubclonesampleapp.util.showToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitHubCloneAppBar(
    title: String,
    isHome: Boolean = true,
    icon: Int? = null,
    navController: NavController,
    onBackPressed: () -> Unit = {},
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (!isHome) {
                IconButton(onClick = { onBackPressed.invoke() }) {
                    if (icon != null) {
                        Icon(painter = painterResource(id = icon), contentDescription = "Icon Back")
                    }
                }
            } else {
                Box {}
            }
        },
        actions = {
            if (isHome) {
                IconButton(onClick = {
                    showToast(
                        context = context,
                        message = "Coming soon",
                    )
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search action",
                        tint = Color.Blue
                    )
                }
                IconButton(onClick = {
                    showToast(
                        context = context,
                        message = "Coming soon",
                    )
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_circle),
                        contentDescription = "Add action",
                        tint = Color.Blue
                    )
                }
                IconButton(onClick = {
                    showDialog.value = !showDialog.value
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = "More actions",
                        tint = Color.Blue
                    )
                }
            } else {
                Box {}
            }
        },
    )

}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd),
    ) {
        var expanded by remember {
            mutableStateOf(true)
        }
        val items = listOf("Refresh")
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                showDialog.value = false
            },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White),
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(text = {
                    Text(
                        text = text,
                        fontWeight = FontWeight.W300,
                    )
                }, onClick = {
                    expanded = false
                    showDialog.value = false
                }
                )
            }
        }
    }
}